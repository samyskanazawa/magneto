package com.magneto.mutants.exceptions.handlers;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.magneto.mutants.exceptions.ErrorMessage;


/**
 * @author samys
 *
 */
@EnableWebMvc
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorMessage> handleIllegalArgumentException(
            IllegalArgumentException ex) {
        return buildResponseEntity(BAD_REQUEST, ex);
    }
    
    /**
     * @param httpStatus
     * @param ex
     * @return
     */
    private ResponseEntity<ErrorMessage> buildResponseEntity(HttpStatus httpStatus, Exception ex) {
    	ErrorMessage errorMessage = new ErrorMessage(httpStatus, ex);
        return new ResponseEntity<>(errorMessage, errorMessage.getStatus());
    }
}
