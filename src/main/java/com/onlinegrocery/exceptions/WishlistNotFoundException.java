package com.onlinegrocery.exceptions;

import com.onlinegrocery.exceptionmessages.ExceptionMessages;

public class WishlistNotFoundException extends RuntimeException {

	public WishlistNotFoundException() {
		super(ExceptionMessages.WISHLIST_NOT_FOUND);
	}

	public WishlistNotFoundException(String message) {
		super(message);
	}

}
