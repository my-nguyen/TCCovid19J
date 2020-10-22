package com.nguyen.tccovid19j;

import android.util.Log;
import android.view.View;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class TotalStatsFragment1 extends TotalStatsFragment {
    private static final String TAG = "TotalStatsFragment1";

    @Override
    void fetchWorld() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.totalStats.setVisibility(View.GONE);

        NetworkClient1.getInstance().fetchWorld(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                binding.progressBar.setVisibility(View.GONE);
                binding.totalStats.setVisibility(View.VISIBLE);

                Log.d(TAG, "fetched World record");
                World world = new World(response);
                bind(world);
            }
        });
    }
}