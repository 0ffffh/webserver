package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;


public class PageGenerator {

    public static String getPage(HttpStatus httpStatus){
        StringBuilder response = new StringBuilder();
        response.append("<html>")
                .append("<head><title>").append(httpStatus.getStatus()+" "+httpStatus.getMessage()).append("</title></head>")
                .append("<body><center><h1>").append(httpStatus.getStatus()+" "+httpStatus.getMessage()).append("</h1></center></body>")
                .append("</html>");
        return response.toString();
    }
}
