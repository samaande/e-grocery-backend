package com.onlinegrocery.exceptions;

import com.onlinegrocery.exceptionmessages.ExceptionMessages;

public class CartNotFoundException extends RuntimeException {

	public CartNotFoundException() {
		super(ExceptionMessages.CART_NOT_FOUND);
	}

	public CartNotFoundException(String message) {
		super(message);
	}

}
