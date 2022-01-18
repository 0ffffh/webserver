package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.request.Request;

import java.io.File;


public class Response {
    private static final String DEFAULT_INDEX_PAGE = "index.html";
    private final String header;
    private File file;


    public Response(String webAppPath, Request request) {
        if (request.getHttpMethod().hasMethod()){

            if(request.getUri().equals("/")){
                file = new File(webAppPath,DEFAULT_INDEX_PAGE);
                System.out.println(file);
            } else {
                file = new File(webAppPath,request.getUri());
                System.out.println(file);
            }

            if(file.isFile() && file.canRead()){
                header = ContentReader.getHeader(HttpStatus.OK,file.getPath(), file.length());
            } else {
                header = ContentReader.getHeader(HttpStatus.NOT_FOUND,"", 0);
            }
        } else {
            header = ContentReader.getHeader(HttpStatus.NOT_IMPLEMENTED,"", 0);
        }

        System.out.println("=============RESPONSE HEADER=========");
        System.out.println(header);
        System.out.println("=====================================");
    }

    public File getFile(){
        return file;
    }

    public String getHeader(){
        return header;
    }

}
