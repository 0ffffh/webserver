package com.k0s.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static final int PORT = 8080;
    private static final String DEFAULT_APP_PATH = "src/main/resources/";
//    private static final String DEFAULT_APP_PATH = new File(".").getAbsolutePath();



    private String appPath;
    private int port;
    private boolean isOpen;


    public Server() {
        this(PORT, DEFAULT_APP_PATH);
    }

    public Server(int port) {
        this(port, DEFAULT_APP_PATH);
    }

    public Server(int port, String path){
        this.port = port;
        this.appPath = path;
        this.isOpen = true;
    }

    public void setPort(int port){
        this.port = port;
    }

    public void setWebAppPath(String path){
        this.appPath = path;
    }


    public void stop(){
        isOpen = false;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)){
            RequestHandler requestHandler = new RequestHandler(appPath);
            while (isOpen){
                try (Socket socket = serverSocket.accept();
                     BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedOutputStream socketWriter = new BufferedOutputStream(socket.getOutputStream()) ){

                    requestHandler.handle(socketReader, socketWriter);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)  {
        Server server = new Server();
        server.start();
    }
}