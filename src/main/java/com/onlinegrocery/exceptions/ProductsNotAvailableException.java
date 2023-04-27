package com.onlinegrocery.exceptions;

import com.onlinegrocery.exceptionmessages.ExceptionMessages;

public class ProductsNotAvailableException extends RuntimeException {
	 
	public ProductsNotAvailableException() {
	super(ExceptionMessages.PRODUCTS_NOT_FOUND);
	}
	 
	public ProductsNotAvailableException(String message) {
	super(message);
	}

}
