package com.techscode.backend.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ErrorResponseHandler extends ResponseEntityExceptionHandler {

    public ErrorResponseHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final BindingResult result = ex.getBindingResult();
        final APIResponse<?> bodyOfResponse = createResponse("Invalid " + result.getObjectName(), result.getAllErrors());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final BindingResult result = ex.getBindingResult();
        final APIResponse<?> bodyOfResponse = createResponse("Invalid " + result.getObjectName(), result.getAllErrors());
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.OK, request);
    }

    public APIResponse<?> createResponse(String error, List<ObjectError> allErrors){
        List<Error> errors = new ArrayList<>();

        for(ObjectError objectError : allErrors){
            if(objectError instanceof FieldError){
                FieldError fieldError = (FieldError) objectError;

                errors.add(new Error(fieldError.getField(), fieldError.getDefaultMessage()));
            } else {
                errors.add(new Error(objectError.getObjectName(), objectError.getDefaultMessage()));
            }
        }

        return APIResponse.failure(error, errors);
    }

}

