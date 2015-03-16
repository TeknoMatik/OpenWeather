package com.rustam.openweather.server.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/**
 * Represents a coordinates object in the daily response
 */
public final class Coordinates implements Serializable {
  private static final long serialVersionUID = -8452960924000886053L;

  @SerializedName("lat")
  private double latitude;

  @SerializedName("lon")
  private double longitude;

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Coordinates{");
    sb.append("latitude=").append(latitude);
    sb.append(", longitude=").append(longitude);
    sb.append('}');
    return sb.toString();
  }
}
