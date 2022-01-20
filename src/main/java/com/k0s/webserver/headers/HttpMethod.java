package com.k0s.webserver.headers;

public enum HttpMethod {
    GET;

    public static boolean hasMethod(String method){
        for (HttpMethod httpMethod : HttpMethod.values()) {
            if (httpMethod.name().equalsIgnoreCase(method)) {
                return true;
            }
        }
        return false;
    }
}
