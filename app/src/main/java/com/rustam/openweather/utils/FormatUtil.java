package com.rustam.openweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.rustam.openweather.R;
import java.text.DecimalFormat;

/**
 * utility methods for different formats
 */
public class FormatUtil {
  private static DecimalFormat decimalFormat = new DecimalFormat("###");
  private static final String CELSIUS = "C";
  private static final String FAHRENHEIT = "F";

  public static String formatTemperature(double kelvin, Context context) {
    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    Boolean isCelsius =
        sharedPref.getBoolean(context.getString(R.string.use_celsius_pref_key), false);
    double value = kelvin - 273.15;
    String temperatureFormat = CELSIUS;
    if (!isCelsius) {
      value = value * 1.8000;
      temperatureFormat = FAHRENHEIT;
    }
    return String.format("%s%s", decimalFormat.format(value), temperatureFormat);
  }

  public static String percentValue(int value) {
    return String.format("%d%%", value);
  }

  public static boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }
}
