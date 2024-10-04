package org.example.HW;

import java.util.Scanner;

public class HttpImageStatusCli {
    public void askStatus(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter HTTP status code");

        String input = scanner.nextLine();
        try {
            int statusCode = Integer.parseInt(input);
            HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();
            downloader.downloadStatusImage(statusCode);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
        } catch (Exception e) {
            System.out.println("There is no image for HTTP status " + input);
        }


    }

    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}
