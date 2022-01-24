package com.k0s.webserver.request;

import com.k0s.webserver.headers.HttpMethod;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void getUri() {
        Request request = Mockito.mock(Request.class);
        Mockito.when(request.getUri()).thenReturn("uriValue");
        assertEquals("uriValue", request.getUri());
    }


    @Test
    void getHttpMethod() {
        Request request = Mockito.mock(Request.class);
        Mockito.when(request.getHttpMethod()).thenReturn(HttpMethod.GET);
        assertEquals(HttpMethod.GET, request.getHttpMethod());
    }

}