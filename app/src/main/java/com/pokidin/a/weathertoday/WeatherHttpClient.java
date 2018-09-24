package com.pokidin.a.weathertoday;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {
    private static final String BASE_URL = "api.openweathermap.org/data/2.5/weather?q=";
    private static final String IMG_URL = "http://openweathermap.org/img/w/";

    public String getWeatherData(String location) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) new URL(BASE_URL + location).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();
            return stringBuffer.toString();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Throwable throwable) {
            }
            try {
                connection.disconnect();
            } catch (Throwable throwable) {
            }
        }
        return null;
    }

    public byte[] getImage(String code) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) new URL(IMG_URL + code).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            inputStream = connection.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while (inputStream.read() != -1) {
                baos.write(buffer);
            }
            return baos.toByteArray();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (Throwable throwable) {
            }
            try {
                connection.disconnect();
            } catch (Throwable throwable) {
            }
        }
        return null;
    }
}
