package com.rustam.openweather.server;

import android.util.Log;
import com.rustam.openweather.server.handlers.BaseJsonHandler;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import java.text.MessageFormat;
import org.json.JSONException;

/**
 * Async HTTP Client for the openweather.com
 */
public final class OpenWeatherClient {
  private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";

  private static AsyncHttpClient sClient = new AsyncHttpClient();

  private static String getBaseUrl() {
    return BASE_URL;
  }

  public static String getAbsoluteUrl(final String relativeUrl) {
    return getBaseUrl() + relativeUrl;
  }

  public static AsyncHttpClient getClient() {
    return sClient;
  }

  public static void request(BaseJsonHandler<?, ?> handler) throws JSONException {
    final String absoluteUrl = getAbsoluteUrl(handler.getUrl());

    final RequestParams params = new RequestParams();
    handler.fillParams(params);
    Log.i(OpenWeatherClient.class.getName(),
        MessageFormat.format("URL: {0}\nRequest:{1}", absoluteUrl, params));
    sClient.get(absoluteUrl, params, handler);
  }
}
