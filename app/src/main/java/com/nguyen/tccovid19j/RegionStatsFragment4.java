package com.nguyen.tccovid19j;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import javax.inject.Inject;

public class RegionStatsFragment4 extends RegionStatsFragment {
    private static final String TAG = "RegionStatsFragment4";

    @Inject
    CovidViewModel4 viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((MyApplication) getActivity().getApplication()).appComponent.inject(this);
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
