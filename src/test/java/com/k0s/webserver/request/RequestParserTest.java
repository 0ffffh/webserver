package com.k0s.webserver.request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {

    String httpRequest = "GET / HTTP/1.1\r\n";
//    String httpRequest = "       ";

//    String httpRequest = "GET / HTTP/1.1\r\n" +
//            "Host: 127.0.0.1:8080\n" +
//            "User-Agent: Mozilla/5.0 (X11; Linux x86_64; rv:95.0) Gecko/20100101 Firefox/95.0\n" +
//            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8\n" +
//            "Accept-Language: en-US,en;q=0.5\n" +
//            "Accept-Encoding: gzip, deflate\n" +
//            "DNT: 1\n" +
//            "Connection: keep-alive\n" +
//            "Upgrade-Insecure-Requests: 1\n" +
//            "Sec-Fetch-Dest: document\n" +
//            "Sec-Fetch-Mode: navigate\n" +
//            "Sec-Fetch-Site: none\n" +
//            "Sec-Fetch-User: ?1\n" +
//            "Sec-GPC: 1\n" +
//            "Pragma: no-cache\n" +
//            "Cache-Control: no-cache\r\n" +
//            "\r\n";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parseRequest() throws IOException {


        InputStream targetStream = new ByteArrayInputStream(httpRequest.getBytes());
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(targetStream);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(targetStream));
        Request request = RequestParser.parseRequest(bufferedReader);

        System.out.println(request.toString());


    }
}