package com.gaifullin.rustam.openweather.server.responses;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseResponse implements Serializable {
  private static final long serialVersionUID = 4133851045592099400L;

  @SerializedName("cod")
  private int code;

  public int getCode() {
    return code;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BaseResponse{");
    sb.append("code=").append(code);
    sb.append('}');
    return sb.toString();
  }
}
