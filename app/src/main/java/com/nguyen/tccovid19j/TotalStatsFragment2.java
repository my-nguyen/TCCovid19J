package com.nguyen.tccovid19j;

import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TotalStatsFragment2 extends TotalStatsFragment {
    private static final String TAG = "TotalStatsFragment2";

    @Override
    void fetchWorld() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.totalStats.setVisibility(View.GONE);

        NetworkClient2.getInstance().fetchWorld(new Callback<WorldGson>() {
            @Override
            public void onResponse(Call<WorldGson> call, Response<WorldGson> response) {
                binding.progressBar.setVisibility(View.GONE);
                binding.totalStats.setVisibility(View.VISIBLE);

                Log.d(TAG, "fetched World record");
                if (response.body() != null) {
                    World world = response.body().data;
                    bind(world);
                }
            }

            @Override
            public void onFailure(Call<WorldGson> call, Throwable t) {
                Log.d(TAG, "onFailure");
            }
        });
    }
}