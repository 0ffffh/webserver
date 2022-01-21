package com.k0s.webserver.response;


import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.request.Request;
import com.k0s.webserver.request.RequestParser;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    void getStatus() throws IOException {
        String appPath = "src/main/resources/";
        String httpRequest = "GET / HTTP/1.1";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(httpRequest.getBytes())));
        Request request = RequestParser.parseRequest(bufferedReader);
        Response response = new Response(appPath,request);

        assertEquals(HttpStatus.OK,response.getHttpStatus());
    }

}