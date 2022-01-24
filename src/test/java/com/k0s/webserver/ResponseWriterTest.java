package com.k0s.webserver;

import com.k0s.webserver.headers.HttpMethod;
import com.k0s.webserver.request.Request;
import com.k0s.webserver.response.ContentReader;
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
        Response response = new ContentReader("src/main/resources/", request);

        assertThrows(NullPointerException.class, ()-> ResponseWriter.writeResponse(null, response));

    }
}