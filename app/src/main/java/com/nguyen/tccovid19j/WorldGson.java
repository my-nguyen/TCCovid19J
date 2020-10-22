package com.nguyen.tccovid19j;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class WorldGson {
    @SerializedName("data")
    public World data;
}

class World {
    @SerializedName("total_cases")
    public String totalCases;
    @SerializedName("recovery_cases")
    public String recoveryCases;
    @SerializedName("death_cases")
    public String deathCases;
    @SerializedName("last_update")
    public String lastUpdate;
    @SerializedName("currently_infected")
    public String currentlyInfected;

    World(JSONObject jsonObject) {
        try {
            JSONObject data = jsonObject.getJSONObject("data");
            totalCases = data.getString("total_cases");
            recoveryCases = data.getString("recovery_cases");
            deathCases = data.getString("death_cases");
            lastUpdate = data.getString("last_update");
            currentlyInfected = data.getString("currently_infected");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}