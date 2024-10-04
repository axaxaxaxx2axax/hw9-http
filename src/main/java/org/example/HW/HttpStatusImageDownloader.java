package org.example.HW;

import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpStatusImageDownloader {
    HttpStatusChecker httpStatusChecker = new HttpStatusChecker();

    @SneakyThrows
    public void downloadStatusImage(int code) {
        try {
            String imhUrl = httpStatusChecker.getStatusImage(code);
            URL url = new URL(imhUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (InputStream inputStream = connection.getInputStream();
                 FileOutputStream outputStream = new FileOutputStream(code + ".jpg")) {

                byte[] buffer = new byte[2048];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                System.out.println("Image downloaded: " + code + ".jpg");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
        try {
            downloader.downloadStatusImage(200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
