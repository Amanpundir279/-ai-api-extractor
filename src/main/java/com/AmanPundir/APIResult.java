package com.AmanPundir;

import java.util.Map;

public class APIResult {

    private String method;
    private String path;
    private Map<String, Object> requestSchema;
    private Map<String, Object> responseSchema;

    public APIResult(String method,
                     String path,
                     Map<String, Object> requestSchema,
                     Map<String, Object> responseSchema) {

        this.method = method;
        this.path = path;
        this.requestSchema = requestSchema;
        this.responseSchema = responseSchema;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getRequestSchema() {
        return requestSchema;
    }

    public Map<String, Object> getResponseSchema() {
        return responseSchema;
    }
}