package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.headers.MimeType;


import java.io.*;
import java.util.Date;

public class ContentReader {


    private static final String CRLF = "\r\n";
    BufferedInputStream fileInputStream;
    HttpStatus httpStatus;
    String header;


    public ContentReader() {
    }

    public String getHeader(){
        return header;
    }

    public HttpStatus getStatus(){
        return httpStatus;
    }

    public BufferedInputStream getContent(){
        return fileInputStream;
    }

    public void read(String webAppPath, String uri) {
        File file = new File(webAppPath,uri);

        try {
            fileInputStream = getFileInputStream(file);
            httpStatus = HttpStatus.OK;
            header = getHeader(httpStatus, file.getPath(), file.length());
        } catch (FileNotFoundException e) {

            System.out.println("File not found " + e);
            httpStatus = HttpStatus.NOT_FOUND;
            setError(httpStatus);
        }
    }

    public void setError(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        String htmlPage = PageGenerator.getPage(httpStatus);
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
        System.out.println(response);
        System.out.println("=====================RESPONSE=====================");

        return response.toString();
    }

}


