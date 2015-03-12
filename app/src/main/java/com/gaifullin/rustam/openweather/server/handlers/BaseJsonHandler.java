package com.gaifullin.rustam.openweather.server.handlers;

import android.util.Log;
import com.gaifullin.rustam.openweather.server.listeners.OnResponseListener;
import com.gaifullin.rustam.openweather.server.requests.BaseRequest;
import com.gaifullin.rustam.openweather.server.responses.BaseResponse;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public abstract class BaseJsonHandler<Request extends BaseRequest, Response extends BaseResponse>
    extends JsonHttpResponseHandler {
  private final static int OK = 200;
  private final static int NOT_FOUND = 404;

  protected List<OnResponseListener<Response>> mResponseListenerList =
      new ArrayList<OnResponseListener<Response>>();
  protected Request mRequest;

  public BaseJsonHandler(final Request request) {
    mRequest = request;
  }

  public BaseJsonHandler<Request, Response> addResponseListener(
      final OnResponseListener<Response> responseListener) {
    mResponseListenerList.add(responseListener);
    return this;
  }

  public abstract String getUrl();

  protected abstract Response onOK(final JSONObject o) throws JSONException;

  public abstract void fillParams(final RequestParams params);

  @Override
  public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
    super.onSuccess(statusCode, headers, jsonResponse);
    Log.d(BaseJsonHandler.class.getName(), MessageFormat.format("Response: {0}", jsonResponse));

    try {
      Response response = onOK(jsonResponse);
      if (response.getCode() == OK) {
        if (!mResponseListenerList.isEmpty()) {
          for (final OnResponseListener<Response> listener : mResponseListenerList) {
            listener.onOk(response);
          }
        }
      } else if (response.getCode() == NOT_FOUND) {
        if (!mResponseListenerList.isEmpty()) {
          for (final OnResponseListener<Response> listener : mResponseListenerList) {
            listener.onNotFound();
          }
        }
      }
    } catch (JSONException e) {
      Log.e(BaseJsonHandler.class.getName(), e.getLocalizedMessage());
    }
  }

  @Override
  public void onFailure(int statusCode, Header[] headers, String responseString,
      Throwable throwable) {
    super.onFailure(statusCode, headers, responseString, throwable);
    onFialure(responseString, throwable);
  }

  @Override
  public void onFailure(int statusCode, Header[] headers, Throwable throwable,
      JSONObject errorResponse) {
    super.onFailure(statusCode, headers, throwable, errorResponse);
    onFialure(throwable.getLocalizedMessage(), throwable);
  }

  private void onFialure(String responseString, Throwable throwable) {
    Log.e(BaseJsonHandler.class.getName(),
        MessageFormat.format("Handler [{0}] failed: [{1}]", getUrl(), responseString), throwable);

    if (!mResponseListenerList.isEmpty()) {
      for (final OnResponseListener<Response> listener : mResponseListenerList) {
        listener.onError(throwable);
      }
    }
  }
}
