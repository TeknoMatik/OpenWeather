package com.rustam.openweather.server.requests;

import com.rustam.openweather.Constants;
import com.loopj.android.http.RequestParams;
import java.io.Serializable;

public abstract class BaseRequest implements Serializable {
  private static final long serialVersionUID = 4252208824252727981L;

  protected void fillParams(RequestParams params) {
    params.put("APPID", Constants.API_KEY);
  }
}
