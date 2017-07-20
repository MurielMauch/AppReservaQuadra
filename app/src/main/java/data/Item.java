package data;

import org.json.JSONObject;

/**
 * Created by Tiago Avellar on 01/07/2017.
 */

public class Item implements JSONPopulator {
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override

    public void populate(JSONObject data) {
        condition= new Condition();
        condition.populate(data.optJSONObject("condition"));
    }
}
