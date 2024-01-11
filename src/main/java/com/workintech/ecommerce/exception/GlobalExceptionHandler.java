package com.workintech.ecommerce.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EcommerceErrorResponse> handleException(GlobalException globalException){
        log.error("Global exception occured! Exception details: " + globalException.getMessage());
        EcommerceErrorResponse EcommerceErrorResponse = new EcommerceErrorResponse(
                globalException.getMessage());
        return new ResponseEntity<>(EcommerceErrorResponse,globalException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<EcommerceErrorResponse> handleException(Exception globalException){
        log.error("Global exception occured! Exception details: " + globalException.getMessage());
        EcommerceErrorResponse EcommerceErrorResponse = new EcommerceErrorResponse(
                globalException.getMessage());
        return new ResponseEntity<>(EcommerceErrorResponse,HttpStatus.BAD_REQUEST);
    }
}