package com.gaifullin.rustam.openweather.ui.activities;

import android.content.Intent;
import android.location.Location;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

public final class MainActivity extends BaseActivity
    implements ChangeCityDialog.ChangeCityDialogListener, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

  private ListView mListView;
  private List<Item> listItem;
  private ForecastAdapter mAdapter;

  private Button mCityButton;
  private TextView mTemperatureTextView;
  private TextView mMinTextView;
  private TextView mMaxTextView;
  private TextView mHumidityTextView;

  private GoogleApiClient mGoogleApiClient;
  private LocationRequest mLocationRequest;

  private Location mLastLocation;

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

    mCityButton = (Button) findViewById(R.id.cityButton);
    mTemperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
    mMinTextView = (TextView) findViewById(R.id.minTextView);
    mMaxTextView = (TextView) findViewById(R.id.maxTextView);
    mHumidityTextView = (TextView) findViewById(R.id.humidityTextView);

    mListView = (ListView) findViewById(R.id.forecastListView);
    listItem = new ArrayList<>();

    mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .build();
  }

  @Override protected int getLayoutResources() {
    return R.layout.activity_main;
  }

  @Override protected void onStart() {
    super.onStart();
    mGoogleApiClient.connect();
  }

  @Override protected void onStop() {
    mGoogleApiClient.disconnect();
    super.onStop();
  }

  @Override
  public void onDialogPositiveClick(String query) {
    DailyRequest request = new DailyRequest(query);
    refreshWeather(request);
    DeviceUtil.showProgressDialog(this, getString(R.string.loading));
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
    DailyRequest dailyRequest = new DailyRequest(location.getLatitude(), location.getLongitude());
    refreshWeather(dailyRequest);
  }

  private void refreshWeather(String city) {
    DailyRequest dailyRequest = new DailyRequest(city);
    refreshWeather(dailyRequest);
  }

  private void refreshWeather(DailyRequest request) {
    try {
      OpenWeatherClient.request(new DailyHandler(request).addResponseListener(mOnResponseListener));
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }

  private void fillControls(Item item, String city) {
    mCityButton.setText(city);
    mTemperatureTextView.setText(
        FormatUtil.formatTemperature(item.getTemperature().getDay(), this));
    mMinTextView.setText(String.format("Min\n%s",
        FormatUtil.formatTemperature(item.getTemperature().getMax(), this)));
    mMaxTextView.setText(String.format("Max\n%s",
        FormatUtil.formatTemperature(item.getTemperature().getMin(), this)));
    mHumidityTextView.setText(FormatUtil.percentValue(item.getHumidity()));
  }

  public void onCityButtonClick(View view) {
    ChangeCityDialog cityDialog = new ChangeCityDialog();
    cityDialog.show(getFragmentManager(), ChangeCityDialog.TAG);
  }

  @Override public void onConnected(Bundle bundle) {
    mLocationRequest = LocationRequest.create();
    mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    mLocationRequest.setInterval(Constants.GPS_UPDATE_TIME);

    LocationServices.FusedLocationApi.requestLocationUpdates(
        mGoogleApiClient, mLocationRequest, this);

    Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
    if (location != null) {
      DeviceUtil.showProgressDialog(this, getString(R.string.loading));
      mLastLocation = location;
      refreshWeather(location);
    } else {
      DeviceUtil.showProgressDialog(this, getString(R.string.loading));
      refreshWeather(Constants.DEFAULT_CITY);
    }
  }

  @Override public void onConnectionSuspended(int i) {
    Log.i(MainActivity.class.getName(), "GoogleApiClient connection has been suspend");
  }

  @Override public void onConnectionFailed(ConnectionResult connectionResult) {
    Log.i(MainActivity.class.getName(), "GoogleApiClient connection has failed");
  }

  @Override public void onLocationChanged(Location location) {
    if (mLastLocation == null || (mLastLocation.getLatitude() != location.getLatitude()
        && mLastLocation.getLongitude() != location.getLongitude())) {
      mLastLocation = location;
      refreshWeather(location);
    }
  }
}
