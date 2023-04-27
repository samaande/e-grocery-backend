package com.onlinegrocery.exceptions;

public class AddressNotFoundException extends RuntimeException {
	public AddressNotFoundException(String msg) {
		super(msg);
	}
}
