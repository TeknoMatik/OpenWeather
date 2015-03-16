package com.rustam.openweather.ui.adapters;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.rustam.openweather.Constants;
import com.rustam.openweather.R;
import com.rustam.openweather.server.entity.Item;
import com.rustam.openweather.utils.FormatUtil;
import com.squareup.picasso.Picasso;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public final class ForecastAdapter extends BaseArrayAdapter<Item> {

  public ForecastAdapter(Activity context, List<Item> itemList) {
    super(context, itemList);
  }

  @Override
  protected int[] getCachedResources() {
    return new int[] {
        R.id.dayTextView, R.id.nightTempTextView, R.id.morningTempTextView, R.id.weatherImageView
    };
  }

  @Override
  protected void initControls(View view, int position, ViewHolder viewHolder) {
    Item item = getItem(position);
    TextView dayTextView = (TextView) view.findViewById(R.id.dayTextView);
    TextView nightTempTextView = (TextView) view.findViewById(R.id.nightTempTextView);
    TextView morningTempTextView = (TextView) view.findViewById(R.id.morningTempTextView);
    ImageView weatherImageView = (ImageView) view.findViewById(R.id.weatherImageView);

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(item.getDateTime() * 1000);

    dayTextView.setText(
        calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
    nightTempTextView.setText(
        FormatUtil.formatTemperature(item.getTemperature().getNight(), getContext()));
    morningTempTextView.setText(
        FormatUtil.formatTemperature(item.getTemperature().getMorning(), getContext()));

    Picasso.with(getActivity())
        .load(String.format(Constants.IMAGE_URL, item.getWeatherList().get(0).getIcon()))
        .into(weatherImageView);
  }

  @Override
  protected int getLayoutId(int position) {
    return R.layout.forecast_item;
  }
}
