package com.gaifullin.rustam.openweather.ui.activities;

import android.app.Activity;
import android.preference.PreferenceFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.gaifullin.rustam.openweather.R;

public final class SettingsActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);
    getFragmentManager().beginTransaction()
        .add(R.id.container, new SettingsFragment())
        .commit();
  }

  public static class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
    }

    @Override public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.preferences);
    }
  }
}
