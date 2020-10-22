package com.nguyen.tccovid19j;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class TotalStatsFragment3 extends TotalStatsFragment {
    private static final String TAG = "TotalStatsFragment3";

    private CovidViewModel3 viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(CovidViewModel3.class);
    }

    @Override
    void fetchWorld() {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.totalStats.setVisibility(View.GONE);

        viewModel.fetchWorld().observe(this, new Observer<World>() {
            @Override
            public void onChanged(World world) {
                binding.progressBar.setVisibility(View.GONE);
                binding.totalStats.setVisibility(View.VISIBLE);

                Log.d(TAG, "fetched World record");
                bind(world);
            }
        });
    }
}