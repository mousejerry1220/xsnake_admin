package org.xsnake.web.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class JsonResult {

	public static final String TYPE_SUCCESS = "success";
	public static final String TYPE_ERROR = "error";
	
	public static String toSuccessJson(){
		return toJson(createResultMap(TYPE_SUCCESS,null));
	}
	
	public static String toSuccessJson(String message){
		try {
			message =  new String(message.getBytes("utf-8"),"iso8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String,Object> map = createResultMap(TYPE_SUCCESS,message);
		return toJson(map);
	}
	
	public static String toSuccessJson(Object object){
		Map<String,Object> map = createResultMap(TYPE_SUCCESS,object);
		return toJson(map);
	}
	
	private static Map<String,Object> createResultMap(String type,Object result){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("status",type);
		map.put("message",result);
		return map;
	}
	
	public static String toErrorJson(String message){
		try {
			message =  new String(message.getBytes("utf-8"),"iso8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String,Object> map = createResultMap(TYPE_ERROR,message);
		return toJson(map);
	}
	
	public static String toErrorJson(Object message){
		Map<String,Object> map = createResultMap(TYPE_ERROR,message);
		return toJson(map);
	}
	
	private static String toJson(Object jsonObject){
		Gson gson = new Gson();
		String json = gson.toJson(jsonObject);
		return json;
	}
	
}
