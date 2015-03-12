package com.gaifullin.rustam.openweather.server.requests;

import com.gaifullin.rustam.openweather.utils.FormatUtil;
import com.loopj.android.http.RequestParams;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class ForecastRequest extends BaseRequest {
    private static final long serialVersionUID = -3495165553279849985L;

    private static final int DAYS = 5;

    private String q;
    private double mLatitude;
    private double mLongitude;

    public ForecastRequest(String q) {
        this.q = q;
    }

    public ForecastRequest(double mLatitude, double mLongitude) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }

    @Override
    public void fillParams(RequestParams params) {
        if (q == null) {
            params.put("lat", mLatitude);
            params.put("lon", mLongitude);
        } else {
            String query;
            if (FormatUtil.isNumeric(q)) {
                query = String.format("%s,USA", q);
            } else {
                query = q;
            }
            params.put("q", query);
        }
        params.put("cnt", DAYS);
    }
}
