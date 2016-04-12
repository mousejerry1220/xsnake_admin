package org.xsnake.web.utils;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;


public class StringUtil extends StringUtils{

	public static String getUUID(){
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid.toUpperCase();
	}
	
	public static String getString(String str){
		try {
			return new String(str.getBytes("UTF-8"),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
}
