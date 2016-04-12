package org.xsnake.web.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.xsnake.web.action.JsonResult;

public class ExceptionResolver extends SimpleMappingExceptionResolver{

	@Override
    public ModelAndView doResolveException(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception ex) {
		
		if(ex instanceof BaseException){
			BaseException ve = (BaseException)ex;
			try {
				printErrorMessage(response,ve.getErrorMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.doResolveException(request, response, handler, ex);
	}

	private void printErrorMessage(HttpServletResponse response,String messages)throws IOException {
		PrintWriter writer = null;
		try{
			response.setStatus(500);
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			writer = response.getWriter();
			String message = JsonResult.toErrorJson(messages);
			writer.write(message);
		    writer.flush();
		}finally{
			if(writer !=null){
				writer.close();
			}
		}
	}
	
	
}
