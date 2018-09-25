package com.pokidin.a.weathertoday;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.pokidin.a.weathertoday.model.Weather;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;

    private TextView hum;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String city = "London,UK";

        cityText = findViewById(R.id.cityText);
        condDescr = findViewById(R.id.condDescr);
        temp = findViewById(R.id.temp);
        hum = findViewById(R.id.hum);
        press = findViewById(R.id.press);
        windSpeed = findViewById(R.id.windSpeed);
        windDeg = findViewById(R.id.windDeg);
        imgView = findViewById(R.id.condIcon);

        JsonWeatherTask task = new JsonWeatherTask();
        task.execute(city);
    }

    private class JsonWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... strings) {
            Weather weather = new Weather();
            String data = new WeatherHttpClient().getWeatherData(strings[0]);

            try {
                weather = JSONWeatherParser.getWeather(data);
                weather.iconData = new WeatherHttpClient().getImage(weather.currentCondition.getIcon());
            } catch (JSONException je) {
                je.printStackTrace();
            }
            return weather;
        }
    }
}
