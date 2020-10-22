package com.nguyen.tccovid19j;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class RegionStatsFragment3 extends RegionStatsFragment {
    private static final String TAG = "RegionStatsFragment3";

    private CovidViewModel3 viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(CovidViewModel3.class);
    }

    @Override
    void fetchPage(int page) {
        binding.progressBar.setVisibility(View.VISIBLE);
        binding.regionStats.setVisibility(View.GONE);

        viewModel.fetchCountries(page).observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                binding.progressBar.setVisibility(View.GONE);
                binding.regionStats.setVisibility(View.VISIBLE);

                Log.d(TAG, "fetched 1 page");
                adapter.update(data);
            }
        });
    }
}
