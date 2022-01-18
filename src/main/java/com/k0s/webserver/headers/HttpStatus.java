package com.k0s.webserver.headers;

import java.util.StringJoiner;

public enum HttpStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented");

    private final int status;
    private final String message;


    HttpStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new StringJoiner(" ")
                .add("HTTP/1.1 " + status)
                .add(message)
                .toString();
    }
}
