package org.xsnake.web.action;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 这里封装了一些特色的方法
 * 
 * 1、和session有关的操作请使用父级的setSessionAttribute,getSessionAttribute,sessionInvalidate 来操作，
 * 这样在集群环境中我们可以通过复写这些操作来达成切换session的存储方式，而不以至于面临大量的修改代码
 * 
 * 2、sendSuccessMessage,sendErrorMessage封装为给客户端AJAX方法返回的结果，
 * 使用该返回值的方法需要增加注解@ResponseBody;
 * 
 * 3、forward，redirect提供给action更加优雅的跳转。
 * forward,让我们的页面与action同在一个目录下，这样在维护与开发中会更加轻松
 * @author Administrator
 *
 */
public abstract class BaseAction implements SessionSupport ,AjaxSupport{

	private String getPackagePath() {
		String className = this.getClass().getPackage().getName();
		String packagePath = className.replaceAll("\\.", "/");
		return  "/WEB-INF/classes/" + packagePath + "/";
	}
	
	protected String forward(String path) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.setAttribute("ctx", "/xsnake_admin");
		request.setAttribute("res", "/xsnake_admin");
		return getPackagePath() + path;
	}
	
	protected String redirect(String path){
		return "redirect:" + path;
	}
	
	public void setSessionAttribute(String key , Object obj){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().setAttribute(key, obj);
	}
	
	public void sessionInvalidate(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().invalidate();
	}
	
	public Object getSessionAttribute(String key){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request.getSession().getAttribute(key);
	}
	
	public String sendSuccessMessage(Object result){
		return sendMessage(JsonResult.toSuccessJson(result));
	}
	
	public String sendErrorMessage(Object result){
		return sendMessage(JsonResult.toErrorJson(result));
	}
	
	private String sendMessage(String result){
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		Writer witer = null;
		try {
			witer = response.getWriter();
			witer.write(result);
			witer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(witer !=null){ 
				try {
					witer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
