package com.onlinegrocery.exceptions;

import com.onlinegrocery.exceptionmessages.ExceptionMessages;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException() {
		super(ExceptionMessages.PRODUCT_NOT_FOUND);
	}

	public ProductNotFoundException(String message) {
		super(message);
	}

}
