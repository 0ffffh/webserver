package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.request.Request;

import java.io.BufferedInputStream;


public class Response {

    private static final String DEFAULT_INDEX_PAGE = "index.html";
    private final ContentReader contentReader = new ContentReader();

    public Response(String webAppPath, Request request) {
        if(request.getUri().equals("/")){
            contentReader.read(webAppPath, DEFAULT_INDEX_PAGE);
        } else {
            contentReader.read(webAppPath, request.getUri());
        }
    }


    public String getHeader() {
        return contentReader.getHeader();
    }

    public HttpStatus getHttpStatus() {
        return contentReader.getStatus();
    }

    public BufferedInputStream getBufferedInputStream() {
        return contentReader.getContent();
    }

}



