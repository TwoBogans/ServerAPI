package io.servertap.paypal;

import lombok.Cleanup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Josh Moore <josh.moore@jmoore.dev> (https://tycrek.com)
 * <p>
 * HTTP utility class.
 */
public class Http {
    public static String GET(String token, String url) throws IOException {
        // Set up connection
        var obj = new URL(url);
        var con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("Authorization", token);
        con.setRequestMethod("GET");

        // Check the response
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {

            // Set up the reader
            @Cleanup var reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            var response = new StringBuilder();
            var inputLine = "";

            // Read the response
            while ((inputLine = reader.readLine()) != null)
                response.append(inputLine);

            return response.toString();
        } else {
            // todo: improve error handling
            System.out.println("GET request not worked");
            System.out.println("GET Response Code :: " + responseCode);
            System.out.println("GET Response Message :: " + con.getResponseMessage());
            throw new IOException("GET request not worked");
        }
    }
}