package com.gaifullin.rustam.openweather.server.entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class Item implements Serializable {
    private static final long serialVersionUID = -8244237877878367713L;

    private Temperature temperature;
    private Wind wind;
    private List<Weather> weatherList;
    private double rain;
    private double snow;
    private double clouds;
    private int humidity;
    private long dateTime;

    public Item(JSONObject o) {
        if (o != null) {
            temperature = new Temperature(o);
            wind = new Wind(o);

            dateTime = o.optLong("dt");
            clouds = o.optDouble("clouds");
            humidity = o.optInt("humidity");
            if (o.has("snow")) {
                snow = o.optDouble("snow");
            }
            if (o.has("rain")) {
                rain = o.optDouble("rain");
            }

            JSONArray jsonArray = o.optJSONArray("weather");
            if (jsonArray != null) {
                weatherList = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject weatherJsonObject = jsonArray.getJSONObject(i);
                        Weather weather = new Weather(weatherJsonObject);
                        weatherList.add(weather);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Wind getWind() {
        return wind;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public double getRain() {
        return rain;
    }

    public double getSnow() {
        return snow;
    }

    public double getClouds() {
        return clouds;
    }

    public int getHumidity() {
        return humidity;
    }

    public long getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Item{");
        sb.append("temperature=").append(temperature);
        sb.append(", wind=").append(wind);
        sb.append(", weatherList=").append(weatherList);
        sb.append(", rain=").append(rain);
        sb.append(", snow=").append(snow);
        sb.append(", clouds=").append(clouds);
        sb.append(", humidity=").append(humidity);
        sb.append(", dateTime=").append(dateTime);
        sb.append('}');
        return sb.toString();
    }
}
