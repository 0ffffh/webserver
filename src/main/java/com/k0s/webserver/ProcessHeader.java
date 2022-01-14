package com.k0s.webserver;

import java.util.Date;

public class ProcessHeader {
    private static final String HTTP_200 = "HTTP/1.1 200 OK";
    private static final String HTTP_404 = "HTTP/1.1 404 Not Found";
    private static final String HTTP_501 = "HTTP/1.1 501 Not Implemented";

    private final Date date = new Date();

    public ProcessHeader() {
    }

    public String getHeader(int status, String path, long length){
        StringBuilder responseHeader = new StringBuilder();
        responseHeader.append(HTTP_200)
                .append("Content-type: " + getContentType(path))
                .append("Content-Length: " + length)
                .append(System.getProperty("line.separator"))
                .append(System.getProperty("line.separator"));

        System.out.println("RESPONSE 200=========================================");
        System.out.println(responseHeader);
        System.out.println("====================================================");
        return responseHeader.toString();
    }


    public String getHeader(int status){
        StringBuilder responseHeader = new StringBuilder();
        String end = "";
        if (status == 404){
            responseHeader.append(HTTP_404);
            end = generate404();
        }
        if (status == 501){
            responseHeader.append(HTTP_501);
            end = generate501();
        }

        responseHeader.append("Date: ").append(date)
                .append("Content-type: " + getContentType(""))
                .append("Content-Length: " + end.length())
                .append(System.getProperty("line.separator"))
                .append(end);

        System.out.println("RESPONSE EROR=========================================");
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

    private String generate404(){
        StringBuilder response = new StringBuilder();
        response.append(System.getProperty("line.separator"))
                .append("<html>")
                .append("<head><title>404 Not Found</title></head>")
                .append("<body><center><h1>404 Not Found</h1></center></body>")
                .append("</html>");

        return response.toString();
    }

    private String generate501(){
        StringBuilder response = new StringBuilder();
        response.append(System.getProperty("line.separator"))
                .append("<html>")
                .append("<head><title>404 Not Found</title></head>")
                .append("<body><center><h1>404 Not Found</h1></center></body>")
                .append("</html>");

        return response.toString();
    }

}
