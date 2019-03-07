package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {

    public static void main(String[] arg) {

        Token token = new Token();

        String word = "test";


        StringBuilder sb = new StringBuilder(), response = new StringBuilder();

        sb.append("http://translate.google.ru/translate_a/single?client=webapp&sl=en&tl=ru&hl=ru&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&pc=1&otf=1&ssel=0&tsel=0&kc=1&tk=");

        sb.append(token.getToken(word)).append("&q=").append(word);
        try {
            URL translateURL = new URL(sb.toString());

            HttpURLConnection http = (HttpURLConnection) translateURL.openConnection();
            http.setRequestProperty("User-Agent","translator");

            int responseCode = http.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
                String inputLine;


                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
            } else {
                System.out.println("GET request not worked: " + responseCode);
            }


            System.out.println("Translation Result: " + response.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
