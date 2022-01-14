package com.k0s.webserver;
import java.io.*;

class HttpWorker {
    private static final String DEFAULT_INDEX_PAGE = "index.html";

    private final String appPath;

    private final ProcessHeader processHeader = new ProcessHeader();

    public HttpWorker(String appPath) {
        this.appPath= appPath;
    }


    public void processingRequest(BufferedOutputStream socketWriter, String request) throws IOException {
        String[] tempRequestArray = request.split(System.getProperty("line.separator"));
        String[] tempHeaderArray = tempRequestArray[0].split(" ");
        String header;

        if(!tempRequestArray[0].startsWith("GET") ){
            header = processHeader.getHeader(501);
            socketWriter.write(header.getBytes(),0,header.length());
        }

        if( tempHeaderArray.length < 3 || tempHeaderArray[1].isEmpty() ){
            header = processHeader.getHeader(404);
            socketWriter.write(header.getBytes(),0,header.length());
        }

        String path = tempHeaderArray[1];
        if (path.equals("/")){
            path = DEFAULT_INDEX_PAGE;
        }
        File requestFile = new File(this.appPath, path);
        if (requestFile.isFile() && requestFile.canRead()){
            header = processHeader.getHeader(200, path, requestFile.length());
//            socketWriter.write(header.getBytes(),0,header.length());
            int length;
            byte[] buff = new byte[8192];
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(requestFile))){
                socketWriter.write(header.getBytes(),0,header.length());
                while ((length = bufferedInputStream.read(buff)) != -1) {
                    socketWriter.write(buff, 0, length);
                }
                socketWriter.flush();
            }  catch (IOException e) {
                System.out.println("Can't upload file " + e + " internal server error");
                header = processHeader.getHeader(500);
                socketWriter.write(header.getBytes(),0,header.length());
            }
        } else {
            header = processHeader.getHeader(404);
            socketWriter.write(header.getBytes(),0,header.length());
        }
    }

}
