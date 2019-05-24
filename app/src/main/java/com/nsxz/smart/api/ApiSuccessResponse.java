package com.nsxz.smart.api;

import java.util.Map;

public class ApiSuccessResponse<T> extends ApiResponse<T> {
    private T body;
    private Map<String,String> links;

    public ApiSuccessResponse(T body, Map<String, String> links) {
        this.body = body;
        this.links = links;
    }

    public ApiSuccessResponse(T body, String linkHeader) {
        this.body = body;
//        this.links=linkHeader
    }
}
