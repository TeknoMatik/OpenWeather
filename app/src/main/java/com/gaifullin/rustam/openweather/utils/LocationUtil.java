package com.gaifullin.rustam.openweather.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.gaifullin.rustam.openweather.Constants;

import java.util.List;

/**
 * Created by rustamgaifullin on 3/12/15.
 */
public class LocationUtil {
    public static LocationManager locationManager;

    public static Location getLocation(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);

        Location l = null;

        for (int i = providers.size() - 1; i >= 0; i--) {
            l = lm.getLastKnownLocation(providers.get(i));
            if (l != null) {
                break;
            }
        }
        return l;
    }

    public static void requestLocation(LocationListener locationListener, Context context) {
        if (locationManager == null) {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        }

        boolean isGPSEnabled = false;
        boolean isNetworkEnabled = false;
        boolean isPassiveProviderEnabled = false;
        try {
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
            isGPSEnabled = false;
        }
        try {
            isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
            isNetworkEnabled = false;
        }
        try {
            isPassiveProviderEnabled = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
        } catch (Exception ex) {
            isPassiveProviderEnabled = false;
        }

        if (isPassiveProviderEnabled) {
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, Constants.GPS_UPDATE_TIME, 0, locationListener);
        }
        if (isNetworkEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, Constants.GPS_UPDATE_TIME, 0, locationListener);
        }
        if (isGPSEnabled) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Constants.GPS_UPDATE_TIME, 0, locationListener);
        }
    }
}
