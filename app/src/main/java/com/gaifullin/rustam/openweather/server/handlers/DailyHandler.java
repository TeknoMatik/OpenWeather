package com.gaifullin.rustam.openweather.server.handlers;

import com.gaifullin.rustam.openweather.server.requests.DailyRequest;
import com.gaifullin.rustam.openweather.server.responses.DailyResponse;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class DailyHandler extends BaseJsonHandler<DailyRequest, DailyResponse> {

  public static final String METHOD_URL = "forecast/daily";

  public DailyHandler(DailyRequest request) {
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
  protected DailyResponse onOK(JSONObject o) throws JSONException {
    Gson gson = new Gson();
    return gson.fromJson(o.toString(), DailyResponse.class);
  }
}
