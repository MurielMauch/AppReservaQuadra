package data;

import org.json.JSONObject;

/**
 * Created by Tiago Avellar on 01/07/2017.
 */

public class Units implements JSONPopulator {
    private String temperature;


    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");
    }
}
