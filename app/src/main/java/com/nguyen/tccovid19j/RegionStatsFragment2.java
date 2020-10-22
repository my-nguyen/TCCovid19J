package com.nguyen.tccovid19j;

import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegionStatsFragment2 extends RegionStatsFragment {
    private static final String TAG = "RegionStatsFragment2";

    @Override
    void fetchPage(int page) {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.regionStats.setVisibility(View.GONE);

        NetworkClient2.getInstance().fetchCountries(page, new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                binding.progressBar.setVisibility(View.GONE);
                binding.regionStats.setVisibility(View.VISIBLE);

                Log.d(TAG, "fetched 1 page");
                Countries countries = response.body();
                adapter.update(countries.data);
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
            }
        });
    }
}
