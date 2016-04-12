package org.xsnake.web.utils;

import java.io.IOException;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpUtil {

	public static String post(String url,String postContent){
		HttpClient httpClient = new HttpClient();
		PostMethod post = new PostMethod(url);
		RequestEntity requestEntity = null;
		try{
			requestEntity = new StringRequestEntity(postContent,"text/xml","UTF-8");
			post.setRequestEntity(requestEntity);
			int statusCode = httpClient.executeMethod(post);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + post.getStatusLine());
				return null;
			}
			byte[] responseBody = post.getResponseBody();
			String result = new String(responseBody,"utf-8");
			return result;
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			post.releaseConnection();
		}
		return null;
	}
	
	
	//mobile, code, password  //register
	//sendCode_Register
	public static void main(String[] args) {
		
//		System.out.println(post("http://180.97.69.100/wencai/user/register","{'mobile':'13888888888','code':'1234','password':'123456'}"));
		
//		Object obj = JSON.toJSON("{'mobile':'123'}");
		
//		System.out.println(post("http://127.0.0.1:8080/wencai_interface/user/resetPassword","{'mobile':'18309203830','code':'4938','newPassword':'123456'}"));
		
//		System.out.println(post("http://127.0.0.1:8080/wencai_interface/sys/lov","{'groupCode':'LOV_AREA'}"));
		
		
		System.out.println(post("http://127.0.0.1:8080/wencai_interface/user/deleteAddress","{'addressId':'aaa'}"));
		
//		java.util.Map map = JSON.parseObject("{'mobile':'123'}");
//		
//		System.out.println(obj);
//		
//		System.out.println(map);
		
		
//		http://dxhttp.c123.cn/tx/?uid=鐢ㄦ埛璐﹀彿&pwd=MD5浣�32瀵嗙爜&mobile=鍙风爜&content=鍐呭
		
		
	}
	
	public static String get(String url){
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		try {
			// 鎵цgetMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + getMethod.getStatusLine());
				return null;
			}
			// 璇诲彇鍐呭
			byte[] responseBody = getMethod.getResponseBody();
			// 澶勭悊鍐呭
			String result = new String(responseBody);
			return result;
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return null;
	}
	
}
