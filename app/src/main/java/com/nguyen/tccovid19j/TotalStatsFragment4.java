package com.nguyen.tccovid19j;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

public class TotalStatsFragment4 extends TotalStatsFragment {
    private static final String TAG = "TotalStatsFragment4";

    @Inject
    CovidViewModel4 viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MyApplication) getActivity().getApplication()).appComponent.inject(this);
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