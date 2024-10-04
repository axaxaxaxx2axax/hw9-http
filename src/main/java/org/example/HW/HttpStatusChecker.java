package org.example.HW;

import lombok.SneakyThrows;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class HttpStatusChecker {

    @SneakyThrows
    public String getStatusImage(int code) {
        try {
            URI uri = new URI("https://http.cat/" + code + ".jpg");
            URL url = uri.toURL();

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 404) {
                throw new Exception("Image not found for status code: " + code);
            }
            return url.toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Invalid URI syntax: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException("IO Exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HttpStatusChecker checker = new HttpStatusChecker();

        try {
            System.out.println(checker.getStatusImage(400));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
