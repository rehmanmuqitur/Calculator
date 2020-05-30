package org.muqit.calculator.exceptions;

import org.muqit.calculator.constants.MessageConstants;

public class NegetiveNumberPresentException extends RuntimeException {

	private static final long serialVersionUID = 113123L;

	public NegetiveNumberPresentException() {
		super(MessageConstants.NEGETIVE_NUMBERS_DEFAULT_MESSAGE);
	}
	
	public NegetiveNumberPresentException(String message) {
		super(message);
	}

}