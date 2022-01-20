package com.k0s.webserver.request;

import com.k0s.webserver.headers.HttpMethod;
import com.k0s.webserver.headers.HttpStatus;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {

    public static Request parseRequest(BufferedReader socketReader) throws IOException {
        Request request = new Request();
        String line = socketReader.readLine();
        parseOptions(line, request);
        while ((line = socketReader.readLine()) != null && !line.isEmpty()){
            parseHeaders(line, request);
        }
        System.out.println(request);

        return request;
    }

    private static void parseHeaders(String line, Request request){
            if(line.contains(":")){
                String[] headers = line.split(":\\s");
                request.setHeaders(headers[0], headers[1].split("[(),;\s]"));
            } else {
                throw new IllegalArgumentException(HttpStatus.BAD_REQUEST.toString());
            }
    }

    private static void parseOptions(String statusLine, Request request) {
        if (statusLine == null || statusLine.isEmpty()) {
            throw new IllegalArgumentException( statusLine + " " + HttpStatus.BAD_REQUEST.toString());
        }
        String[] options = statusLine.split("\\s");
        if (options.length != 3 || !options[1].startsWith("/") || !options[2].toUpperCase().startsWith("HTTP/")) {
            throw new IllegalArgumentException(statusLine + " " + HttpStatus.BAD_REQUEST.toString());
        }
        if (!HttpMethod.hasMethod(options[0])){
            throw new IllegalStateException(options[0] + " " + HttpStatus.NOT_IMPLEMENTED.toString());
        }
        request.setHttpMethod(HttpMethod.valueOf(options[0]));
        request.setUri(options[1]);
        request.setHttpVersion(options[2]);
    }

}