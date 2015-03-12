package com.gaifullin.rustam.openweather.server.requests;

import com.loopj.android.http.RequestParams;
import java.io.Serializable;

public abstract class BaseRequest implements Serializable {
  private static final long serialVersionUID = 4252208824252727981L;

  public abstract void fillParams(RequestParams params);
}
