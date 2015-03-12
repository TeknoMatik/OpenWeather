package com.gaifullin.rustam.openweather.server.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class City implements Serializable {
  private static final long serialVersionUID = -6268004874642363625L;

  private int id;

  private String name;

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
