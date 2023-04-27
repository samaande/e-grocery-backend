package com.onlinegrocery.exceptions;
 
import java.util.HashMap;
import java.util.Map;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
    @Override
    protected ResponseEntity<Object> 
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
 
    @ExceptionHandler(Exception.class)
    public String exceptionHanlder(Exception ex)
    {
        return ex.getMessage();
    }
    @ExceptionHandler(AppUserException.class)
    public ResponseEntity<?> hanldeAppUserException(AppUserException ex)
    {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
    }
 
}

