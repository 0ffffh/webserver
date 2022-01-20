package com.k0s.webserver.request;

import com.k0s.webserver.headers.HttpMethod;

import java.util.HashMap;
import java.util.Map;


public class Request {
    private String uri;
    private HttpMethod httpMethod;
    private String httpVersion;
    private Map<String, String[]> headers = new HashMap<>();

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public Map<String, String[]> getHeaders() {
        return headers;
    }

    public void setHeaders(String key, String[] value) {
        this.headers.put(key, value);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(httpMethod).append(" ").append(uri).append(" ").append(httpVersion);
        for (Map.Entry<String, String[]> entry : headers.entrySet()) {
            sb.append("\r\n").append(entry.getKey()).append(": ");
            for(String val: entry.getValue()){
                sb.append(val).append(" ");
            }
        }
        return sb.toString();
    }
}
