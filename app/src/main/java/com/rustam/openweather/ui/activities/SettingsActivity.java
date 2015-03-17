package com.rustam.openweather.ui.activities;
import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.os.Bundle;

import android.view.MenuItem;
import com.rustam.openweather.R;

public final class SettingsActivity extends BaseActivity {
  public static final int SETTINGS_NOT_CHANGED_CODE = 0;
  public static final int SETTINGS_CHANGED_CODE = 1;

  private static Boolean mChanged;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getFragmentManager().beginTransaction()
        .add(R.id.container, new SettingsFragment())
        .commit();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override protected int getLayoutResources() {
    return R.layout.activity_settings;
  }

  public static class SettingsFragment extends PreferenceFragment implements
      SharedPreferences.OnSharedPreferenceChangeListener {



    public SettingsFragment() {
    }

    @Override public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      mChanged = false;
      addPreferencesFromResource(R.xml.preferences);
    }

    @Override
    public void onResume() {
      super.onResume();
      getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    }

    @Override
    public void onPause() {
      getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
      super.onPause();
    }

    @Override public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
        String key) {
        if (key.equals(getString(R.string.use_celsius_pref_key))) {
          mChanged = true;
        }
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch(id) {
      case android.R.id.home:
        int resultCode = mChanged ? SETTINGS_CHANGED_CODE : SETTINGS_NOT_CHANGED_CODE;
        setResult(resultCode);
        supportFinishAfterTransition();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
