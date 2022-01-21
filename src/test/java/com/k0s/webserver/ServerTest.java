package com.k0s.webserver;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ServerTest {

    Server server = new Server();

    @Test
    @Disabled
    void setUp() {
        server.setPort(8080);
        server.setWebAppPath("/");
        server.start();
    }

}