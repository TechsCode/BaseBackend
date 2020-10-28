package com.techscode.backend.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIResponse<DATA> {

    public static APIResponse<?> success(String message){
        return success(message, null);
    }

    public static <DATA> APIResponse<DATA> success(String message, DATA data){
        return custom("SUCCESS", message, data);
    }

    public static <DATA> APIResponse<DATA> custom(String status, String message, DATA data){
        return new APIResponse<>(status, message, new ArrayList<>(), data);
    }

    public static APIResponse<?> failure(Error... errors){
        return failure(Arrays.asList(errors));
    }

    public static APIResponse<?> failure(List<Error> errors){
        return failure("No message provided.", errors);
    }

    public static APIResponse<?> failure(String message, Error... errors){
        return failure(message, Arrays.asList(errors));
    }

    public static APIResponse<?> failure(String message, List<Error> errors){
        return new APIResponse<>("FAILURE", message, errors, null);
    }

    private final String status;
    private final String message;
    private final List<Error> errors;
    private final DATA data;

    private APIResponse(String status, String message, List<Error> errors, DATA data) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public DATA getData() {
        return data;
    }
}
