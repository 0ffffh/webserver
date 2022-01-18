package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;

import java.util.Date;

public class ContentReader {


    public static String getHeader(HttpStatus httpStatus, String path, long length){
        Date date = new Date();
        String errorPage = "";
        long contentLength = length;
        if (path == null || path.equals("") || path.lastIndexOf(".") < 0) {
            errorPage = generateErrorPage(httpStatus);
            contentLength = errorPage.length();
        }

        StringBuilder response = new StringBuilder();
        response.append(httpStatus.toString())
                .append("\r\n").append("Date: ").append(date).append("\r\n")
                .append("Content-type: " + getContentType(path)).append("\r\n")
                .append("Content-Length: " + contentLength).append("\r\n")
                .append("\r\n")
                .append(errorPage);
        return response.toString();
    }


    private static String generateErrorPage(HttpStatus httpStatus){
        StringBuilder response = new StringBuilder();
        response.append("<html>")
                .append("<head><title>").append(httpStatus.getStatus()+" "+httpStatus.getMessage()).append("</title></head>")
                .append("<body><center><h1>").append(httpStatus.getStatus()+" "+httpStatus.getMessage()).append("</h1></center></body>")
                .append("</html>");
        return response.toString();
    }


    // переделать enum
    private static String getContentType(String path){
        if (path == null || path.equals("") || path.lastIndexOf(".") < 0) {
            return "text/html";
        }

        String extension = path.substring(path.lastIndexOf("."));

        if(extension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if(extension.equalsIgnoreCase(".htm")) {
            return "text/html";
        }
        if(extension.equalsIgnoreCase(".css")) {
            return "text/css";
        }
        if(extension.equalsIgnoreCase(".ico")) {
            return "image/x-icon .ico";
        }
        if(extension.equalsIgnoreCase(".jpg")) {
            return "image/jpg";
        }
        if(extension.equalsIgnoreCase(".png")) {
            return "image/png";
        }
        if(extension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        return "text/plain";
    }
}

