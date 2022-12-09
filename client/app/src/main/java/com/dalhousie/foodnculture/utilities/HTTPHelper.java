package com.dalhousie.foodnculture.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHelper {

    public static StringBuffer performHttpRequest(String url, String method) throws Exception {
        HttpURLConnection connection = null;
        StringBuffer response = null;
        try {
            URL getUrl = new URL(url);
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response.toString());
            }
        }catch (Exception ex) {
            throw ex;
        }finally {
            connection.disconnect();
        }
        return response;
    }

    public static StringBuffer performHttpRequest(String url, String method, String jsonData) throws Exception {
        HttpURLConnection connection = null;
        StringBuffer response = null;
        try {
            URL getUrl = new URL(url);
            connection = (HttpURLConnection) getUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write(jsonData.getBytes("UTF-8"));
            os.close();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                System.out.println(response.toString());
            }
        }catch (Exception ex) {
            throw ex;
        }finally {
            connection.disconnect();
        }
        return response;
    }

}
