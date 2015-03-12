package com.gaifullin.rustam.openweather.server.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.json.JSONObject;

/**
 * Represents a city object in the daily response
 */
public final class City implements Serializable {
  private static final long serialVersionUID = -6268004874642363625L;

  @SerializedName("id")
  private int id;

  @SerializedName("name")
  private String name;

  @SerializedName("country")
  private String country;

  @SerializedName("coord")
  private Coordinates coordinates;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  public Coordinates getCoordinates() {
    return coordinates;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("City{");
    sb.append("id=").append(id);
    sb.append(", name='").append(name).append('\'');
    sb.append(", country='").append(country).append('\'');
    sb.append(", coordinates=").append(coordinates);
    sb.append('}');
    return sb.toString();
  }
}
