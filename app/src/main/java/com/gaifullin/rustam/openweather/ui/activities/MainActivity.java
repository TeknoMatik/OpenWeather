package com.gaifullin.rustam.openweather.ui.activities;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.gaifullin.rustam.openweather.Constants;
import com.gaifullin.rustam.openweather.R;
import com.gaifullin.rustam.openweather.server.OpenWeatherClient;
import com.gaifullin.rustam.openweather.server.entity.Item;
import com.gaifullin.rustam.openweather.server.handlers.DailyHandler;
import com.gaifullin.rustam.openweather.server.listeners.OnResponseListener;
import com.gaifullin.rustam.openweather.server.requests.DailyRequest;
import com.gaifullin.rustam.openweather.server.responses.DailyResponse;
import com.gaifullin.rustam.openweather.ui.adapters.ForecastAdapter;
import com.gaifullin.rustam.openweather.ui.dialogs.ChangeCityDialog;
import com.gaifullin.rustam.openweather.utils.DeviceUtil;
import com.gaifullin.rustam.openweather.utils.FormatUtil;
import com.gaifullin.rustam.openweather.utils.LocationUtil;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

public final class MainActivity extends ActionBarActivity
    implements ChangeCityDialog.ChangeCityDialogListener {

  private ListView mListView;
  private List<Item> listItem;
  private ForecastAdapter mAdapter;

  private Button mCityButton;
  private TextView mTemperatureTextView;
  private TextView mMinTextView;
  private TextView mMaxTextView;
  private TextView mHumidityTextView;

  private Location mFoundLocation;

  private Boolean mIsFirstTime = true;

  public LocationListener mLocationListener = new LocationListener() {
    public void onLocationChanged(Location location) {
      mFoundLocation = location;
      refreshWeather(location);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onProviderDisabled(String provider) {

    }
  };

  private OnResponseListener<DailyResponse> mOnResponseListener =
      new OnResponseListener<DailyResponse>() {
        @Override
        public void onOk(DailyResponse response) {
          listItem = response.getItemList();
          mAdapter = new ForecastAdapter(MainActivity.this, listItem);
          mListView.setAdapter(mAdapter);
          mAdapter.notifyDataSetChanged();

          fillControls(response.getItemList().get(0), response.getCity().getName());
          DeviceUtil.hideProgressDialog();
        }

        @Override
        public void onNotFound() {
          DeviceUtil.hideProgressDialog();
          Toast.makeText(MainActivity.this, getString(R.string.city_not_found), Toast.LENGTH_SHORT)
              .show();
        }

        @Override
        public void onError(Throwable throwable) {
          Log.d(MainActivity.class.getName(), throwable.getLocalizedMessage());
          DeviceUtil.hideProgressDialog();
          Toast.makeText(MainActivity.this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT)
              .show();
        }
      };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mCityButton = (Button) findViewById(R.id.cityButton);
    mTemperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
    mMinTextView = (TextView) findViewById(R.id.minTextView);
    mMaxTextView = (TextView) findViewById(R.id.maxTextView);
    mHumidityTextView = (TextView) findViewById(R.id.humidityTextView);

    mListView = (ListView) findViewById(R.id.forecastListView);
    listItem = new ArrayList<>();
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (mIsFirstTime) {
      LocationUtil.requestLocation(mLocationListener, this);
      new PerformLocationTask().execute();
      mIsFirstTime = false;
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    LocationUtil.locationManager.removeUpdates(mLocationListener);
    mIsFirstTime = true;
  }

  @Override
  public void onDialogPositiveClick(String query) {
    DailyRequest request = new DailyRequest(query);
    refreshWeather(request);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.action_settings) {
      Intent intent = new Intent(this, SettingsActivity.class);
      startActivity(intent);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void refreshWeather(Location location) {
    DailyRequest dailyRequest =
        new DailyRequest(location.getLatitude(), location.getLongitude());
    refreshWeather(dailyRequest);
  }

  private void refreshWeather(String city) {
    DailyRequest dailyRequest = new DailyRequest(city);
    refreshWeather(dailyRequest);
  }

  private void refreshWeather(DailyRequest request) {
    try {
      DeviceUtil.showProgressDialog(this, getString(R.string.loading));
      OpenWeatherClient.request(
          new DailyHandler(request).addResponseListener(mOnResponseListener));
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void fillControls(Item item, String city) {
    mCityButton.setText(city);
    mTemperatureTextView.setText(FormatUtil.formatTemperature(item.getTemperature().getDay(), this));
    mMinTextView.setText(
        String.format("Min\n%s", FormatUtil.formatTemperature(item.getTemperature().getMax(), this)));
    mMaxTextView.setText(
        String.format("Max\n%s", FormatUtil.formatTemperature(item.getTemperature().getMin(), this)));
    mHumidityTextView.setText(FormatUtil.percentValue(item.getHumidity()));
  }

  public void onCityButtonClick(View view) {
    ChangeCityDialog cityDialog = new ChangeCityDialog();
    cityDialog.show(getFragmentManager(), ChangeCityDialog.TAG);
  }

  private class PerformLocationTask extends AsyncTask<Void, Void, Void> {
    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      DeviceUtil.showProgressDialog(MainActivity.this, getString(R.string.getting_location));
    }

    @Override
    protected Void doInBackground(Void... params) {
      Long currentTime = System.currentTimeMillis();
      while (mFoundLocation == null) {
        try {
          Thread.sleep(300);
        } catch (Exception e) {
          Log.e(MainActivity.class.getName(), e.getMessage(), e);
        }
        if (System.currentTimeMillis() - currentTime > 5 * 1000) {
          break;
        }
      }
      return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      DeviceUtil.hideProgressDialog();
      Location location = LocationUtil.getLocation(MainActivity.this);
      if (location != null) {
        refreshWeather(location);
      } else {
        Toast.makeText(MainActivity.this, getString(R.string.using_default_location),
            Toast.LENGTH_SHORT).show();
        refreshWeather(Constants.DEFAULT_CITY);
      }
    }
  }
}
