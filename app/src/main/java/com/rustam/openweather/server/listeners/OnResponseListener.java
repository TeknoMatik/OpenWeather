package com.rustam.openweather.server.listeners;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public interface OnResponseListener<T> {
  void onOk(final T response);

  void onNotFound();

  void onError(final Throwable throwable);
}
