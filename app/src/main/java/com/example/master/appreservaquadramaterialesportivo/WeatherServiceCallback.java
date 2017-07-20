package com.example.master.appreservaquadramaterialesportivo;

import data.Channel;

/**
 * Created by Tiago Avellar on 01/07/2017.
 */

public interface WeatherServiceCallback {
    void serviceSucess(Channel channel);

    void serviceFailure(Exception exception);

}
