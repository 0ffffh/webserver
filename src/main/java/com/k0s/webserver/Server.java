package com.k0s.webserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {
    private static final int PORT = 8080;
    private static final String DEFAULT_APP_PATH = "src/main/resources/";

    private HttpWorker httpWorker;

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

    public void start() throws IOException {
        httpWorker = new HttpWorker(this.appPath);
        listen();
    }

    public void stop(){
        isOpen = false;
    }

    private void listen() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(this.port)){
            while (isOpen){
                try (Socket socket = serverSocket.accept();
                     BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedOutputStream socketWriter = new BufferedOutputStream(socket.getOutputStream()) ){

                    String line;
                    StringBuilder request = new StringBuilder();
                    while (!(line = socketReader.readLine()).isEmpty()) {
                        request.append(line);
                        request.append(System.getProperty("line.separator"));
                    }
                    System.out.println(request);

                    if (!request.isEmpty()){
                        httpWorker.processingRequest(socketWriter,request.toString());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }
}