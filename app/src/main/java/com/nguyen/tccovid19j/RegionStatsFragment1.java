package com.nguyen.tccovid19j;

import android.util.Log;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegionStatsFragment1 extends RegionStatsFragment {
    private static final String TAG = "RegionStatsFragment1";

    @Override
    void fetchPage(int page) {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.regionStats.setVisibility(View.GONE);

        NetworkClient1.getInstance().fetchCountries(page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                binding.progressBar.setVisibility(View.GONE);
                binding.regionStats.setVisibility(View.VISIBLE);

                Log.d(TAG, "fetched 1 page");
                Countries countries = new Countries(response);
                adapter.update(countries.data);
            }
        });
    }
}
