package com.gaifullin.rustam.openweather.server.responses;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class BaseResponse implements Serializable {

  private static final long serialVersionUID = 4133851045592099400L;

  private int code;

  public BaseResponse(final JSONObject o) throws JSONException {
    if (o != null) {
      code = o.optInt("cod");
    }
  }

  public int getCode() {
    return code;
  }

  protected BaseResponse() {
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BaseResponse{");
    sb.append("code=").append(code);
    sb.append('}');
    return sb.toString();
  }
}
