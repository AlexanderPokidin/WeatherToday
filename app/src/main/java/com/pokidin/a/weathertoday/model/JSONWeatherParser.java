package com.pokidin.a.weathertoday.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONWeatherParser {

    public static Weather getWeather(String data) throws JSONException{
        Weather weather = new Weather();

        JSONObject jsonObject = new JSONObject(data);
        Location location = new Location();

        JSONObject coordObject = getObject("coord", jsonObject);
        location.setLatitude(getFloat("lat", coordObject));
        location.setLongitude(getFloat("lon", coordObject));

        JSONObject sysObject = getObject("sys", jsonObject);
        location.setCountry(getString("county", sysObject));
        location.setCity(getString("city", sysObject));
        location.setSunrise(getInt("sunrise", sysObject));
        location.setSunset(getInt("sunset", sysObject));
        weather.location = location;

        JSONArray jsonArray = jsonObject.getJSONArray("weather");

        JSONObject jsonWeather = jsonArray.getJSONObject(0);
        weather.currentCondition.setWeatherId(getInt("id", jsonWeather));
        weather.currentCondition.setDescr(getString("description", jsonWeather));
        weather.currentCondition.setCondition(getString("main", jsonWeather));
        weather.currentCondition.setIcon(getString("icon", jsonWeather));

        JSONObject mainObject = getObject("main", jsonWeather);
        weather.currentCondition.setHumidity(getInt("humidity", mainObject));
        weather.currentCondition.setPressure(getInt("pressure", mainObject));
        weather.temperature.setMaxTemp(getFloat("temp_max", mainObject));
        weather.temperature.setMinTemp(getFloat("temp_min", mainObject));
        weather.temperature.setTemp(getFloat("temp", mainObject));

        JSONObject windObject = getObject("wind", jsonObject);
        weather.wind.setSpeed(getFloat("speed", windObject));
        weather.wind.setDeg(getFloat("deg", windObject));

        JSONObject cloudObject = getObject("clouds", jsonObject);
        weather.clouds.setPerc(getInt("all", cloudObject));

        return weather;
    }

    private static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getJSONObject(tagName);
    }

    private static String getString (String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    private static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }
}
