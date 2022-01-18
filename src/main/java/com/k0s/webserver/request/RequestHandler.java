package com.k0s.webserver.request;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.response.Response;
import com.k0s.webserver.response.ResponseWriter;

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
        } catch (IOException e) {

            ResponseWriter.writeError(socketWriter, HttpStatus.BAD_REQUEST);
            e.printStackTrace();
        }

        Response response = new Response(webAppPath, request);

//        System.out.println(response.getHeader());
//        System.out.println(response.getFile());

        ResponseWriter.writeResponse(socketWriter, response);
    }
}
