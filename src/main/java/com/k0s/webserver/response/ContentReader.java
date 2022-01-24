package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.headers.MimeType;
import com.k0s.webserver.request.Request;


import java.io.*;
import java.util.Date;

public class ContentReader implements Response {


    private static final String CRLF = "\r\n";
    private static final String DEFAULT_INDEX_PAGE = "index.html";
    private BufferedInputStream fileInputStream;
    private HttpStatus httpStatus;
    private String header;

    public ContentReader(String webAppPath, Request request) {
        try {
            if(request.getUri().equals("/")){
                read(webAppPath, DEFAULT_INDEX_PAGE);
            } else {
                read(webAppPath, request.getUri());
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            setError(httpStatus);
        }
    }


    public ContentReader() {
    }

    @Override
    public String getHeader(){
        return header;
    }

    @Override
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    @Override
    public BufferedInputStream getContent(){
        return fileInputStream;
    }

    public void read(String webAppPath, String uri) {
//        File file = new File(webAppPath,uri);

        try {
            File file = new File(webAppPath,uri);
            fileInputStream = getFileInputStream(file);
            httpStatus = HttpStatus.OK;
            header = getHeader(httpStatus, file.getPath(), file.length());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            httpStatus = HttpStatus.NOT_FOUND;
            setError(httpStatus);
        }
    }

    public void setError(HttpStatus httpStatus){
        if (httpStatus == null){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        this.httpStatus = httpStatus;
        String htmlPage = PageGenerator.getPage(this.httpStatus);
        header = getHeader(httpStatus, "", htmlPage.length());
        InputStream targetStream = new ByteArrayInputStream(htmlPage.getBytes());
        fileInputStream = new BufferedInputStream(targetStream);
    }

    private BufferedInputStream getFileInputStream(File file) throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    private String getHeader(HttpStatus httpStatus, String path, long contentLength){
        Date date = new Date();

        StringBuilder response = new StringBuilder();
        response.append(httpStatus.toString()).append(CRLF)
                .append("Date: ").append(date).append(CRLF)
                .append("Content-type: " + MimeType.getByPath(path)).append(CRLF)
                .append("Content-Length: " + contentLength).append(CRLF)
                .append(CRLF);

        System.out.println("=====================RESPONSE=====================");
        System.out.println(path);
        System.out.print(response);
        System.out.println("=====================RESPONSE=====================");

        return response.toString();
    }

}


