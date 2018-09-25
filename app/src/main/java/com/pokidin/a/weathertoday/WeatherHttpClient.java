package com.pokidin.a.weathertoday;

import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherHttpClient {
    private static final String TAG = "DebugRun";

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String IMG_URL = "http://openweathermap.org/img/w/";
    private static final String KEY = "&appid=d96cb8dcee64ae658a8ccd5c09dcf58f";

    public String getWeatherData(String location) {
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {
            connection = (HttpURLConnection) new URL(BASE_URL + location + KEY).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            Log.d(TAG, "HttpURLConnection connected");

            StringBuffer stringBuffer = new StringBuffer();
            inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\r\n");
            }

            inputStream.close();
            connection.disconnect();
            Log.d(TAG, "HttpURLConnection disconnected");

            return stringBuffer.toString();

        } catch (IOException ioe) {
            Log.e(TAG, ioe.toString());
            ioe.printStackTrace();
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

//    public byte[] getImage(String code) {
//        HttpURLConnection connection = null;
//        InputStream inputStream = null;
//
//        try {
//            connection = (HttpURLConnection) new URL(IMG_URL + code + ".png").openConnection();
//            connection.setRequestMethod("GET");
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.connect();
//            Log.d(TAG, "ImageURLConnection connected");
//
//            inputStream = connection.getInputStream();
//            byte[] buffer = new byte[1024];
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//            while (inputStream.read() != -1) {
//                baos.write(buffer);
//            }
//            return baos.toByteArray();
//        } catch (IOException ioe) {
//            Log.e(TAG, ioe.toString());
//            ioe.printStackTrace();
//        } finally {
//            try {
//                inputStream.close();
//            } catch (Throwable throwable) {
//            }
//            try {
//                connection.disconnect();
//            } catch (Throwable throwable) {
//            }
//        }
//        Log.d(TAG, "ImageURLConnection disconnected");
//        return null;
//    }
}
