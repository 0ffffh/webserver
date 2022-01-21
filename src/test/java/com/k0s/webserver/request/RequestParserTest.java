package com.k0s.webserver.request;


import com.k0s.webserver.headers.HttpMethod;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {


    @Test
    @DisplayName("Request parser normal request")
    void parseRequest() throws IOException {

        String httpRequest = "GET / HTTP/1.1";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(httpRequest.getBytes())));
        Request request = RequestParser.parseRequest(bufferedReader);

        assertEquals(httpRequest, request.toString());
        assertEquals(HttpMethod.GET, request.getHttpMethod());
        assertEquals("/", request.getUri());
        assertEquals("HTTP/1.1", request.getHttpVersion());


        System.out.println(request);
    }

    @Test
    @DisplayName("500 Not implemented")
    void parseNotImplementedRequest() {

        String httpRequest = "xDDD / HTTP/1.1";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(httpRequest.getBytes())));

        assertThrows(IllegalStateException.class, ()->  RequestParser.parseRequest(bufferedReader));

    }

    @Test
    @DisplayName("400 Bad request exception")
    void parseNullRequest() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream("".getBytes())));

        assertThrows(IllegalArgumentException.class, ()->  RequestParser.parseRequest(bufferedReader));

    }

    @Test
    @DisplayName("400 Bad request exception")
    void parseBadRequestNoUri() {

        String httpRequest = "GET  HTTP/1.1";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(httpRequest.getBytes())));
        assertThrows(IllegalArgumentException.class, ()->  RequestParser.parseRequest(bufferedReader));

        assertThrows(IllegalArgumentException.class, ()->  RequestParser.parseRequest(bufferedReader));

    }

    @Test
    @DisplayName("400 Bad request exception")
    void parseBadRequestBadUri() {

        String httpRequest = "GET 55 HTTP/1.1";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(httpRequest.getBytes())));
        assertThrows(IllegalArgumentException.class, ()->  RequestParser.parseRequest(bufferedReader));

        assertThrows(IllegalArgumentException.class, ()->  RequestParser.parseRequest(bufferedReader));

    }
}
