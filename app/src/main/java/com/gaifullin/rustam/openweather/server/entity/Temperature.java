package com.gaifullin.rustam.openweather.server.entity;

import com.google.gson.annotations.SerializedName;
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

  @SerializedName("eve")
  private double evening;

  @SerializedName("morn")
  private double morning;

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
