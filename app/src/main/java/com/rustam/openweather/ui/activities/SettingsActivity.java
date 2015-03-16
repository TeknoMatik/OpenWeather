package com.rustam.openweather.ui.activities;
import android.preference.PreferenceFragment;
import android.os.Bundle;

import com.gaifullin.rustam.openweather.R;

public final class SettingsActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getFragmentManager().beginTransaction()
        .add(R.id.container, new SettingsFragment())
        .commit();
  }

  @Override protected int getLayoutResources() {
    return R.layout.activity_settings;
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
