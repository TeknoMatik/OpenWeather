package com.gaifullin.rustam.openweather.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Window;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class DeviceUtil {

  private static final AtomicInteger sCountStartDialog = new AtomicInteger(0);
  private static ProgressDialog sDialog;

  public static void showProgressDialog(final Activity activity, final String msg) {
    if (activity != null && !activity.isFinishing() && !activity.isRestricted()) {
      activity.runOnUiThread(new Runnable() {
        @Override
        public void run() {
          if (!activity.isFinishing()) {
            synchronized (sCountStartDialog) {
              sCountStartDialog.incrementAndGet();
              if (sDialog == null || sCountStartDialog.get() < 1) {
                sDialog = new ProgressDialog(activity);
                sDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                sDialog.setIndeterminate(true);
                sDialog.setCancelable(false);
                sDialog.setCanceledOnTouchOutside(false);
                sDialog.setMessage(msg);
                sDialog.show();
              }
            }
          }
        }
      });
    }
  }

  public static void hideProgressDialog() {
    synchronized (sCountStartDialog) {
      if (sCountStartDialog.get() != 0) {
        sCountStartDialog.decrementAndGet();
      }
      if (sDialog != null && sDialog.isShowing() && sCountStartDialog.get() == 0) {
        try {
          sDialog.dismiss();
          sDialog = null;
        } catch (IllegalArgumentException e) {
          Log.e(DeviceUtil.class.getName(), e.getMessage(), e);
        } finally {
          sDialog = null;
        }
      }
    }
  }
}
