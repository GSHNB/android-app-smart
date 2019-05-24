package com.nsxz.smart.api;

public final class ApiErrorResponse<T> extends ApiResponse<T> {
    private String errorMessage;


    public ApiErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
