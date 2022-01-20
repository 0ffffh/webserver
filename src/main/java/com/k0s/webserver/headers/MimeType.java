package com.k0s.webserver.headers;

public enum MimeType {
    html("text/html"),
    htm("text/html"),
    css("text/css"),
    ico("image/x-icon"),
    jpeg("image/jpeg"),
    jpg("image/jpeg"),
    text("text/plain");


    public final String contentType;


    MimeType(String contentType) {

        this.contentType = contentType;
    }

    public static String getByPath(String path) {
        MimeType mimeType = html;
        if (path == null || path.equals("") || path.lastIndexOf(".") <= 0) {
            return mimeType.contentType;
        }
        String extension = path.substring(path.lastIndexOf(".")+1);
        for (MimeType mt : MimeType.values()) {
            if (mt.name().equalsIgnoreCase(extension)) {
                mimeType = mt;
            }
        }
        return mimeType.contentType;
    }
}
