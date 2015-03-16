package com.rustam.openweather.server.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

/**
 * Represents an item object in the daily response
 */
public final class Item implements Serializable {
  private static final long serialVersionUID = -8244237877878367713L;

  @SerializedName("temp")
  private Temperature temperature;

  @SerializedName("weather")
  private List<Weather> weatherList;

  @SerializedName("speed")
  private double speed;

  @SerializedName("deg")
  private double direction;

  @SerializedName("rain")
  private double rain;

  @SerializedName("snow")
  private double snow;

  @SerializedName("clouds")
  private double clouds;

  @SerializedName("humidity")
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

  @Override public String toString() {
    final StringBuilder sb = new StringBuilder("Item{");
    sb.append("temperature=").append(temperature);
    sb.append(", weatherList=").append(weatherList);
    sb.append(", speed=").append(speed);
    sb.append(", direction=").append(direction);
    sb.append(", rain=").append(rain);
    sb.append(", snow=").append(snow);
    sb.append(", clouds=").append(clouds);
    sb.append(", humidity=").append(humidity);
    sb.append(", dateTime=").append(dateTime);
    sb.append('}');
    return sb.toString();
  }
}
