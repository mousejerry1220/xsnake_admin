package org.xsnake.web.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class MessageContext implements ApplicationContextAware {

	private static ApplicationContext context = null;
	
	@Override
	public void setApplicationContext(ApplicationContext context)throws BeansException {
		
		MessageContext.context = context;
		
	}
	
	public static String getMessage(String code,String... args){
		
		return MessageContext.context.getMessage(code, args, null);
		
	}
	
	public static Object getBean(String beanName){
		
		return MessageContext.context.getBean(beanName);
		
	}

}
