package com.gaifullin.rustam.openweather.server.entity;

import java.io.Serializable;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class Temperature implements Serializable {
  private static final long serialVersionUID = -1793322847419321760L;
  private double day;
  private double min;
  private double max;
  private double night;
  private double evening;
  private double morning;

  public Temperature(JSONObject o) {
    JSONObject temperatureJsonObject = o.optJSONObject("temp");
    if (temperatureJsonObject != null) {
      day = temperatureJsonObject.optDouble("day");
      min = temperatureJsonObject.optDouble("min");
      max = temperatureJsonObject.optDouble("max");
      night = temperatureJsonObject.optDouble("night");
      evening = temperatureJsonObject.optDouble("evening");
      morning = temperatureJsonObject.optDouble("morn");
    }
  }

  public double getDay() {
    return day;
  }

  public double getMin() {
    return min;
  }

  public double getMax() {
    return max;
  }

  public double getNight() {
    return night;
  }

  public double getEvening() {
    return evening;
  }

  public double getMorning() {
    return morning;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Temperature{");
    sb.append("day=").append(day);
    sb.append(", min=").append(min);
    sb.append(", max=").append(max);
    sb.append(", night=").append(night);
    sb.append(", evening=").append(evening);
    sb.append(", morning=").append(morning);
    sb.append('}');
    return sb.toString();
  }
}
