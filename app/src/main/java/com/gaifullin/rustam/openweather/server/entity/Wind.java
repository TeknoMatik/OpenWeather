package com.gaifullin.rustam.openweather.server.entity;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by rustamgaifullin on 3/11/15.
 */
public class Wind implements Serializable{
    private static final long serialVersionUID = 9058804939287130650L;

    private double speed;
    private double direction;

    public Wind(JSONObject o) {
        if (o != null) {
            this.speed = o.optDouble("speed");
            this.direction = o.optDouble("deg");
        }
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Wind{");
        sb.append("speed=").append(speed);
        sb.append(", direction=").append(direction);
        sb.append('}');
        return sb.toString();
    }
}
