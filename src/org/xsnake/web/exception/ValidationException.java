package org.xsnake.web.exception;

import org.springframework.validation.BindingResult;

public class ValidationException extends BaseException {

	public ValidationException(BindingResult result) {
		super(result);
	}
	
	public ValidationException(String code){
		super(code);
	}

	private static final long serialVersionUID = 1L;
	
}
