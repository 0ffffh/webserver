package com.k0s.webserver;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.request.Request;
import com.k0s.webserver.request.RequestParser;
import com.k0s.webserver.response.Response;

import java.io.*;

public class RequestHandler {

    private final String webAppPath;


    public RequestHandler(String webAppPath){
        this.webAppPath = webAppPath;
    }

    public void handle(BufferedReader socketReader, BufferedOutputStream socketWriter) throws IOException {
        Request request = new Request();
        try {
            request = RequestParser.parseRequest(socketReader);
        } catch (Exception e) { ///
            System.out.println(e);
            ResponseWriter.writeResponse(socketWriter, HttpStatus.valueOf(e.toString()));
            return;
        }

        Response response = new Response(webAppPath, request);
        ResponseWriter.writeResponse(socketWriter, response);
    }
}
