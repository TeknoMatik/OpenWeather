package com.gaifullin.rustam.openweather.server.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class City implements Serializable {
  private static final long serialVersionUID = -6268004874642363625L;

  private int id;
  private String name;
  private String country;
  private Coordinates coordinates;


  public City(JSONObject o) {
    JSONObject cityJsonObject = o.optJSONObject("city");
    if (cityJsonObject != null) {
      this.id = cityJsonObject.optInt("id");
      this.name = cityJsonObject.optString("name");
      this.country = cityJsonObject.optString("country");
      this.coordinates = new Coordinates(cityJsonObject);
    }
  }

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
