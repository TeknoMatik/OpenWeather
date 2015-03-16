package com.rustam.openweather.ui.activities;
import android.preference.PreferenceFragment;
import android.os.Bundle;

import android.view.MenuItem;
import com.rustam.openweather.R;

public final class SettingsActivity extends BaseActivity {

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

  public static class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
    }

    @Override public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.preferences);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    switch(id) {
      case android.R.id.home:
        supportFinishAfterTransition();
        return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
