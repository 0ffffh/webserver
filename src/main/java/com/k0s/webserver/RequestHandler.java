package com.k0s.webserver;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.request.Request;
import com.k0s.webserver.request.RequestParser;
import com.k0s.webserver.response.Response;

import java.io.*;

public class RequestHandler {

    private final String webAppPath;
    private Request request;

    public RequestHandler(String webAppPath){
        this.webAppPath = webAppPath;
    }

    public void handle(BufferedReader socketReader, BufferedOutputStream socketWriter) throws IOException {

        try {
            request = RequestParser.parseRequest(socketReader);
        } catch (Exception e) { ///
            e.printStackTrace();
            ResponseWriter.writeResponse(socketWriter, HttpStatus.BAD_REQUEST);
            return;
        }

        Response response = new Response(webAppPath, request);
        ResponseWriter.writeResponse(socketWriter, response);
    }
}
