package com.AmanPundir;

public class APIEndpoint {

    private String method;
    private String path;
    private String codeSnippet;

    private String requestSchema;
    private String responseSchema;

    public APIEndpoint(String method, String path, String codeSnippet) {
        this.method = method;
        this.path = path;
        this.codeSnippet = codeSnippet;
    }

    public String getMethod() { return method; }
    public String getPath() { return path; }
    public String getCodeSnippet() { return codeSnippet; }

    public String getRequestSchema() { return requestSchema; }
    public String getResponseSchema() { return responseSchema; }

    public void setRequestSchema(String requestSchema) {
        this.requestSchema = requestSchema;
    }

    public void setResponseSchema(String responseSchema) {
        this.responseSchema = responseSchema;
    }
}