package com.onlinegrocery.exceptions;
 
import com.onlinegrocery.exceptionmessages.ExceptionMessages;
 
public class UserNotFoundException extends RuntimeException {
 
public UserNotFoundException() {
super(ExceptionMessages.USER_NOT_FOUND);
}
 
public UserNotFoundException(String message) {
super(message);
}
 
}

