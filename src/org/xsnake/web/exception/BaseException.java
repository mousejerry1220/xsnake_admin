package org.xsnake.web.exception;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.xsnake.web.context.MessageContext;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String errorMessage = " "; 
	
	public BaseException(String code,String... message){
		errorMessage = MessageContext.getMessage(code,message);
	}
	
	public BaseException(String code){
		errorMessage = MessageContext.getMessage(code);
	}
	
	public BaseException(BindingResult result){
		List<FieldError> errors = result.getFieldErrors();
		for(FieldError error : errors){
			errorMessage = errorMessage + MessageContext.getMessage(error.getDefaultMessage()) + " ";
		}
	}
	
	public String getErrorMessage(){
		return errorMessage;
	}
	
}
