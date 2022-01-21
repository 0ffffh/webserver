package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class ContentReaderTest {
    ContentReader contentReader = new ContentReader();

    @Test
    void testRead200getStatus() {
        contentReader.read("src/main/resources","index.html");
        assertEquals(HttpStatus.OK, contentReader.getStatus());
    }

    @Test
    void testRead404getStatus() {
        contentReader.read("src/main/resources","");
        assertEquals(HttpStatus.NOT_FOUND, contentReader.getStatus());
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
        assertEquals(HttpStatus.NOT_IMPLEMENTED, contentReader.getStatus());
    }
}