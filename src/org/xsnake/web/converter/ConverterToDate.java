package org.xsnake.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class ConverterToDate implements Converter<String, Date>{

	@Override
	public Date convert(String source){
		if(StringUtils.isEmpty(source)){
			return null;
		}
		Date result = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			result = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
		return result;
	}

}
