package com.api.systemaccount.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(Exception.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage()
        ), HttpStatus.BAD_REQUEST);
    }

}
