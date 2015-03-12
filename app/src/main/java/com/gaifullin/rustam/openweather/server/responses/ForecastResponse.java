package com.gaifullin.rustam.openweather.server.responses;

import com.gaifullin.rustam.openweather.server.entity.City;
import com.gaifullin.rustam.openweather.server.entity.Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class ForecastResponse extends BaseResponse {

  private static final long serialVersionUID = -3652426689846372212L;

  private City city;
  private List<Item> itemList;

  public ForecastResponse(JSONObject o) throws JSONException {
    super(o);

    if (o != null) {
      this.city = new City(o);

      JSONArray jsonArray = o.optJSONArray("list");
      if (jsonArray != null) {
        itemList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
          JSONObject itemJsonObject = jsonArray.getJSONObject(i);
          Item item = new Item(itemJsonObject);
          itemList.add(item);
        }
      }
    }
  }

  public City getCity() {
    return city;
  }

  public List<Item> getItemList() {
    return itemList;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ForecastResponse{");
    sb.append("city=").append(city);
    sb.append(", itemList=").append(itemList);
    sb.append('}');
    return sb.toString();
  }
}
