package com.gaifullin.rustam.openweather.server.handlers;

import com.gaifullin.rustam.openweather.server.requests.ForecastRequest;
import com.gaifullin.rustam.openweather.server.responses.ForecastResponse;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class ForecastHandler extends BaseJsonHandler<ForecastRequest, ForecastResponse> {

  public static final String METHOD_URL = "forecast/daily";

  public ForecastHandler(ForecastRequest request) {
    super(request);
  }

  @Override
  public void fillParams(RequestParams params) {
    mRequest.fillParams(params);
  }

  @Override
  public String getUrl() {
    return METHOD_URL;
  }

  @Override
  protected ForecastResponse onOK(JSONObject o) throws JSONException {
    Gson gson = new Gson();
    return gson.fromJson(o.toString(), ForecastResponse.class);
  }
}
