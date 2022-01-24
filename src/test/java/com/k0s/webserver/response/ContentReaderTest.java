package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContentReaderTest {
    ContentReader contentReader = new ContentReader();

    @Test
    void testRead200getStatus() {
        contentReader.read("src/main/resources","index.html");
        assertEquals(HttpStatus.OK, contentReader.getHttpStatus());
    }

    @Test
    void testRead404getStatus() {
        contentReader.read("src/main/resources","");
        assertEquals(HttpStatus.NOT_FOUND, contentReader.getHttpStatus());
    }

    @Test
    void getContent() throws IOException {
        contentReader.read("src/main/resources","index.html");
        File file = new File("src/main/resources/index.html");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Files.readAllBytes(file.toPath()));
        assertArrayEquals(byteArrayInputStream.readAllBytes(), contentReader.getContent().readAllBytes());
    }

    @Test
    void setError() {
        contentReader.setError(HttpStatus.NOT_IMPLEMENTED);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, contentReader.getHttpStatus());
    }


    @Mock
    Response mockResponse = mock(ContentReader.class);

    @Test
    void mGetContent() throws IOException {
        BufferedInputStream mockInputStream = mock(BufferedInputStream.class);

        when(mockResponse.getContent()).thenReturn(mockInputStream);
        when(mockInputStream.readAllBytes()).thenReturn("content".getBytes());

        assertArrayEquals("content".getBytes(), mockInputStream.readAllBytes());

    }

    @Test
    void mGetResponse(){
        when(mockResponse.getHttpStatus())
                .thenReturn(HttpStatus.OK)
                .thenReturn(HttpStatus.BAD_REQUEST)
                .thenReturn(HttpStatus.NOT_FOUND)
                .thenReturn(HttpStatus.NOT_IMPLEMENTED)
                .thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        assertEquals(HttpStatus.OK, mockResponse.getHttpStatus());
        assertEquals(HttpStatus.BAD_REQUEST, mockResponse.getHttpStatus());
        assertEquals(HttpStatus.NOT_FOUND, mockResponse.getHttpStatus());
        assertEquals(HttpStatus.NOT_IMPLEMENTED, mockResponse.getHttpStatus());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, mockResponse.getHttpStatus());
    }
}