package com.k0s.webserver;

import com.k0s.webserver.headers.HttpMethod;
import com.k0s.webserver.request.Request;
import com.k0s.webserver.response.Response;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ResponseWriterTest {

    @Test
    void writeResponseHttp() {
    }

    @Test
    void WriteResponseResponse() throws IOException {
        Request request = new Request();
        request.setHttpMethod(HttpMethod.GET);
        request.setUri("index.html");
        Response response = new Response("src/main/resources/", request);
//        ResponseWriter.writeResponse(null, response);

//        int i = 0;
//        OutputStream targetStream = new ByteArrayOutputStream(i);
//        BufferedOutputStream socketWriter = new BufferedOutputStream(targetStream);


        assertThrows(IOException.class, ()-> ResponseWriter.writeResponse(null, response));

//        assertThrows(IOException.class, ()-> ResponseWriter.writeResponse(socketWriter, response));
    }
}