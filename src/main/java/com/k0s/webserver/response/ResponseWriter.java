package com.k0s.webserver.response;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.response.ContentReader;
import com.k0s.webserver.response.Response;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ResponseWriter {


    public static void writeResponse(BufferedOutputStream socketWriter, Response response) throws IOException {
        String header = response.getHeader();
        socketWriter.write(header.getBytes(),0,header.length());
        int length;
        byte[] buff = new byte[8192];
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(response.getFile()))){

            while ((length = bufferedInputStream.read(buff)) != -1) {
                socketWriter.write(buff, 0, length);
            }
            socketWriter.flush();
        }  catch (IOException e) {
            System.out.println("Can't upload file " + e + " internal server error");
            writeError(socketWriter, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public static void writeError(BufferedOutputStream socketWriter, HttpStatus httpStatus) throws IOException {
        String errorPage = ContentReader.getHeader(httpStatus,"", 0);
        socketWriter.write(errorPage.getBytes(),0, errorPage.length());
    }

}
