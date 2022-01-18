package com.k0s.webserver.request;

//import com.k0s.webserver.HttpMethod;
import com.k0s.webserver.headers.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {

    public static Request parseRequest(BufferedReader socketReader) throws IOException {
        String line;
        Request request = new Request();

        parseOptions(socketReader.readLine(), request);

        while (!(line = socketReader.readLine()).isEmpty()) {
            if(line.contains(":")){
                String[] headers = line.split(":");
                request.setHeaders(headers[0], headers[1].split("[,;]\\s"));
                // тут переделать нормально
            }
        }
        System.out.println(request);

        return request;
    }
    private static void parseOptions(String s, Request request){
        String[] options = s.split("\\s");
        if (options.length != 3 || !options[1].startsWith("/") || !options[2].toUpperCase().startsWith("HTTP/")) {
            throw new IllegalArgumentException();
        }
        request.setHttpMethod(HttpMethod.valueOf(options[0])); // IllegalArgument ??
        request.setUri(options[1]);
        request.setHttpVersion(options[2]);
    }

}