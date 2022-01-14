package com.k0s.webserver;

import java.util.Date;

public class ProcessHeader {
    private static final String HTTP_200 = "HTTP/1.1 200 OK";
    private static final String HTTP_404 = "HTTP/1.1 404 Not Found";
    private static final String HTTP_500 = "HTTP/1.1 500 Internal Server Error";
    private static final String HTTP_501 = "HTTP/1.1 501 Not Implemented";


    private final Date date = new Date();

    public ProcessHeader() {
    }

    public String getHeader(int status, String path, long length){
        StringBuilder responseHeader = new StringBuilder();
        responseHeader.append(HTTP_200)
                .append("Content-type: " + getContentType(path))
                .append("Content-Length: " + length)
                .append("\r\n\r\n");


        System.out.println("====================================================");
        System.out.println(responseHeader);
        System.out.println("====================================================");
        return responseHeader.toString();
    }


    public String getHeader(int status){
        StringBuilder responseHeader = new StringBuilder();
        String end = "";
        if (status == 404){
            responseHeader.append(HTTP_404);
            end = generateErrorPage(HTTP_404);
        }
        if (status == 500){
            responseHeader.append(HTTP_500);
            end = generateErrorPage(HTTP_500);
        }
        if (status == 501){
            responseHeader.append(HTTP_501);
            end = generateErrorPage(HTTP_501);
        }

        responseHeader.append("Date: ").append(date)
                .append("Content-type: " + getContentType(""))
                .append("Content-Length: " + end.length())
                .append("\r\n\r\n")
                .append(end);

        System.out.println("====================================================");
        System.out.println(responseHeader);
        System.out.println("====================================================");

        return responseHeader.toString();
    }

    private String getContentType(String path){
        if (path == null || path.equals("") || path.lastIndexOf(".") < 0) {
            return "text/html";
        }

        String extension = path.substring(path.lastIndexOf("."));

        if(extension.equalsIgnoreCase(".html")) {
            return "text/html";
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

    private String generateErrorPage(String error){
        StringBuilder response = new StringBuilder();
                response.append("<html>")
                .append("<head><title>").append(error.substring(9)).append("</title></head>")
                .append("<body><center><h1>").append(error.substring(9)).append("</h1></center></body>")
                .append("</html>");

        return response.toString();
    }

}
