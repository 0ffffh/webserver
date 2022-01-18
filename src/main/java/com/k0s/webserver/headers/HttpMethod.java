package com.k0s.webserver.headers;

public enum HttpMethod {
    GET("GET");

    private final String method;

    public boolean hasMethod(){
        return method != null;
    }

    public String getMethod() {
        return method;
    }

    HttpMethod(String method) {
        this.method = method;
    }
}
