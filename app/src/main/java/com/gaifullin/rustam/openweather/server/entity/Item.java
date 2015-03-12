package com.gaifullin.rustam.openweather.server.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class Item implements Serializable {
  private static final long serialVersionUID = -8244237877878367713L;

  private Temperature temperature;

  @SerializedName("weather")
  private List<Weather> weatherList;

  private double speed;

  @SerializedName("deg")
  private double direction;

  private double rain;

  private double snow;

  private double clouds;

  private int humidity;

  @SerializedName("dt")
  private long dateTime;

  public Temperature getTemperature() {
    return temperature;
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
