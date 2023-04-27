package com.onlinegrocery.exceptions;

import com.onlinegrocery.exceptionmessages.ExceptionMessages;

public class ProductIdNotFoundException extends RuntimeException {

	public ProductIdNotFoundException() {
		super(ExceptionMessages.PRODUCT_ID_NOT_FOUND);
	}

	public ProductIdNotFoundException(String message) {
		super(message);
	}

}
