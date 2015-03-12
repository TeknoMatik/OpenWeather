package com.gaifullin.rustam.openweather.utils;

import java.text.DecimalFormat;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class FormatUtil {

  private static DecimalFormat decimalFormat = new DecimalFormat("###");

  public static String formatTemperature(double kelvin) {
    double value = Math.round((kelvin - 273.15) * 1.8000);
    return String.format("%sF", decimalFormat.format(value));
  }

  public static String percentValue(int value) {
    return String.format("%d%%", value);
  }

  public static boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }
}
