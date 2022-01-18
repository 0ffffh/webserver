package com.k0s.webserver;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class ServerTest {

    Server server = new Server();

    @BeforeEach
    void setUp() {
        server.setPort(8080);
        server.setWebAppPath("/home/k0s/tmp/webApp");
    }

    @AfterEach
    void tearDown() {
        server.stop();
    }

    @Test
    void startServer() throws IOException {
//        Server server = new Server();
//        server.setPort(8080);
//        server.setWebAppPath("/home/k0s/tmp/webApp");
        server.start();
    }

    @Test
    void stopServer() throws IOException {
//        Server server = new Server();
//        server.setPort(8080);
//        server.setWebAppPath("/home/k0s/tmp/webApp");
        server.stop();
    }

}