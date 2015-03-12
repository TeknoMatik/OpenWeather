package com.gaifullin.rustam.openweather.server.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class Coordinates implements Serializable {
  private static final long serialVersionUID = -8452960924000886053L;

  private double latitude;
  private double longitude;

  public Coordinates(JSONObject o) {
    JSONObject coordinatesJsonObject = o.optJSONObject("coord");
    if (coordinatesJsonObject != null) {
      this.latitude = coordinatesJsonObject.optDouble("lat");
      this.longitude = coordinatesJsonObject.optDouble("lon");
    }
  }

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
