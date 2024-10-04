package org.example;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

public class HttpClient {
    public static void main(String[] args) {
        try {
            URI uri = new URI("https",
                    "jsonplaceholder.typicode.com", "/todos/1", null);

            URL url = uri.toURL();

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Response content: " + response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
