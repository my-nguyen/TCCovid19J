package com.nguyen.tccovid19j;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Countries {
    @SerializedName("data")
    public Data data;

    Countries(JSONObject jsonObject) {
        try {
            data = new Data(jsonObject.getJSONObject("data"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

class Data {
    @SerializedName("paginationMeta")
    public Pagination pagination;
    @SerializedName("last_update")
    public String lastUpdate;
    @SerializedName("rows")
    public List<Country> countries = null;

    Data(JSONObject jsonObject) {
        try {
            pagination = new Pagination(jsonObject.getJSONObject("paginationMeta"));
            lastUpdate = jsonObject.getString("last_update");
            countries = Country.fromJsonArray(jsonObject.getJSONArray("rows"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

class Pagination {
    @SerializedName("currentPage")
    public Integer currentPage;
    @SerializedName("currentPageSize")
    public Integer currentPageSize;
    @SerializedName("totalPages")
    public Integer totalPages;
    @SerializedName("totalRecords")
    public Integer totalRecords;

    Pagination(JSONObject jsonObject) {
        try {
            currentPage = jsonObject.getInt("currentPage");
            totalPages = jsonObject.getInt("totalPages");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

class Country implements Serializable {
    @SerializedName("country")
    public String country;
    @SerializedName("country_abbreviation")
    public String countryAbbreviation;
    @SerializedName("total_cases")
    public String totalCases;
    @SerializedName("total_deaths")
    public String totalDeaths;
    @SerializedName("total_recovered")
    public String totalRecovered;
    @SerializedName("active_cases")
    public String activeCases;
    @SerializedName("flag")
    public String flag;

    Country(JSONObject jsonObject) {
        try {
            country = jsonObject.getString("country");
            countryAbbreviation = jsonObject.getString("country_abbreviation");
            totalCases = jsonObject.getString("total_cases");
            totalDeaths = jsonObject.getString("total_deaths");
            totalRecovered = jsonObject.getString("total_recovered");
            activeCases = jsonObject.getString("active_cases");
            flag = jsonObject.getString("flag");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    static List<Country> fromJsonArray(JSONArray jsonArray) {
        List<Country> countries = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                Country country = new Country(jsonArray.getJSONObject(i));
                countries.add(country);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return countries;
    }
}
