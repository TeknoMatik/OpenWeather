package com.rustam.openweather.ui.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import com.rustam.openweather.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rustamgaifullin on 3/14/15.
 */
abstract class BaseActivity extends ActionBarActivity {
  protected Toolbar mToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResources());
    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }
  }

  protected abstract int getLayoutResources();

  protected List<Pair<View, String>> getDefaultPair() {
    List<Pair<View, String>> pairs = new ArrayList<>();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      pairs.add(Pair.create(findViewById(android.R.id.navigationBarBackground),
          Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
      pairs.add(Pair.create(findViewById(android.R.id.statusBarBackground),
          Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
    }
    pairs.add(Pair.create((View) mToolbar, "toolbar"));

    return pairs;
  }
}
