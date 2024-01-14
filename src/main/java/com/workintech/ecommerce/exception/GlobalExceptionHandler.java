package com.workintech.ecommerce.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Set;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
  /*  @ExceptionHandler
    public ResponseEntity<EcommerceErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder errorMessageBuilder = new StringBuilder("Validation failed: ");

        for (ConstraintViolation<?> violation : violations) {
            errorMessageBuilder.append(violation.getMessageTemplate()).append(", ");
        }

        // Son iki karakteri (virgül ve boşluk) kaldırma
        String errorMessage = errorMessageBuilder.substring(0, errorMessageBuilder.length() - 2);

        log.error("Validation failed! Exception details: " + errorMessage);

        EcommerceErrorResponse ecommerceErrorResponse = new EcommerceErrorResponse(errorMessage);
        return new ResponseEntity<>(ecommerceErrorResponse, HttpStatus.BAD_REQUEST);
    }
*/
    @ExceptionHandler
    public ResponseEntity<EcommerceErrorResponse> handleException(GlobalException globalException) {
        EcommerceErrorResponse exceptionResponse = new EcommerceErrorResponse("apiException(custom) occured: " + globalException.getMessage());
        return new ResponseEntity<>(exceptionResponse, globalException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<EcommerceErrorResponse> handleException(Exception exception) {
        EcommerceErrorResponse exceptionResponse = new EcommerceErrorResponse(exception.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}