package com.nguyen.tccovid19j;

import android.util.Log;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CovidMapFragment1 extends CovidMapFragment {
    private static final String TAG = "CovidMapFragment1";

    @Override
    void getAllCountries() {
        CountriesAdapter adapter = CountriesAdapter.getInstance(getContext());
        binding.progressBar.setVisibility(View.VISIBLE);
        // binding.covidMap.setVisibility(View.GONE);

        if (adapter.totalPages != 0 && adapter.currentPage == adapter.totalPages) {
            // all countries have been pre-fetched, just load the map
            loadMap();
        } else {
            NetworkClient1.getInstance().fetchCountries(adapter.currentPage + 1, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    Countries countries = new Countries(response);
                    adapter.update(countries.data);
                    Log.d(TAG, "fetched " + adapter.countries.size() + " countries");
                    Pagination pagination = countries.data.pagination;
                    if (pagination.currentPage < pagination.totalPages) {
                        getAllCountries();
                    } else {
                        // all countries are fetched. now load the map
                        loadMap();
                    }
                }
            });
        }
    }
}