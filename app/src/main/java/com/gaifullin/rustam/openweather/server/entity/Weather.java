package com.gaifullin.rustam.openweather.server.entity;

import java.io.Serializable;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class Weather implements Serializable {
  private static final long serialVersionUID = 4974414852153081305L;

  private long id;
  private String main;
  private String description;
  private String icon;

  public Weather(JSONObject o) {

    if (o != null) {
      this.id = o.optLong("id");
      this.main = o.optString("main");
      this.description = o.optString("description");
      this.icon = o.optString("icon");
    }
  }

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
