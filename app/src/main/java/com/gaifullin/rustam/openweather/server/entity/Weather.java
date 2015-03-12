package com.gaifullin.rustam.openweather.server.entity;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.json.JSONObject;

/**
 * Represents a weather object in the daily response
 */
public final class Weather implements Serializable {
  private static final long serialVersionUID = 4974414852153081305L;

  @SerializedName("id")
  private long id;

  @SerializedName("main")
  private String main;

  @SerializedName("description")
  private String description;

  @SerializedName("icon")
  private String icon;

  public long getId() {
    return id;
  }

  public String getMain() {
    return main;
  }

  public String getDescription() {
    return description;
  }

  public String getIcon() {
    return icon;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Weather{");
    sb.append("id=").append(id);
    sb.append(", main='").append(main).append('\'');
    sb.append(", description='").append(description).append('\'');
    sb.append(", icon='").append(icon).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
