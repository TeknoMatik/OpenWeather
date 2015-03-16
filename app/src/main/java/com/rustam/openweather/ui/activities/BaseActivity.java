package com.rustam.openweather.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import com.gaifullin.rustam.openweather.R;

/**
 * Created by rustamgaifullin on 3/14/15.
 */
abstract class BaseActivity extends ActionBarActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResources());
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }
  }

  protected abstract int getLayoutResources();
}
