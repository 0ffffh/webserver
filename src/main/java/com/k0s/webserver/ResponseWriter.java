package com.k0s.webserver;

import com.k0s.webserver.headers.HttpStatus;
import com.k0s.webserver.response.ContentReader;
import com.k0s.webserver.response.Response;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class ResponseWriter {


    public static void writeResponse(BufferedOutputStream socketWriter, Response response) throws IOException {
        try (BufferedInputStream bufferedInputStream = response.getBufferedInputStream()){

            writeToSocket(socketWriter,bufferedInputStream, response.getHeader());

        }  catch (IOException e) {
            System.out.println("Can't upload file " + e + " internal server error");
            writeResponse(socketWriter, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void writeResponse(BufferedOutputStream socketWriter, HttpStatus httpStatus) throws IOException {
        ContentReader contentReader = new ContentReader();
        contentReader.setError(httpStatus);
        writeToSocket(socketWriter, contentReader.getContent(), contentReader.getHeader());
    }

    private static void writeToSocket(BufferedOutputStream socketWriter,
                                      BufferedInputStream bufferedInputStream, String header) throws IOException {
        socketWriter.write(header.getBytes(),0,header.length());
        int length;
        byte[] buff = new byte[8192];
        while ((length = bufferedInputStream.read(buff)) != -1) {
            socketWriter.write(buff, 0, length);
        }
        socketWriter.flush();
    }

}
