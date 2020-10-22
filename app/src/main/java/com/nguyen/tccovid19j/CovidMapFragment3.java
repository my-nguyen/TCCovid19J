package com.nguyen.tccovid19j;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class CovidMapFragment3 extends CovidMapFragment {
    private static final String TAG = "CovidMapFragment3";

    private CovidViewModel3 viewModel;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(CovidViewModel3.class);
    }

    @Override
    void getAllCountries() {
        CountriesAdapter adapter = CountriesAdapter.getInstance(getContext());
        binding.progressBar.setVisibility(View.VISIBLE);
        // binding.covidMap.setVisibility(View.GONE);

        if (adapter.totalPages != 0 && adapter.currentPage == adapter.totalPages) {
            // all countries have been pre-fetched, just load the map
            loadMap();
        } else {
            viewModel.fetchCountries(adapter.currentPage+1).observe(this, new Observer<Data>() {
                @Override
                public void onChanged(Data data) {
                    adapter.update(data);
                    Log.d(TAG, "fetched " + adapter.countries.size() + " countries");
                    Pagination pagination = data.pagination;
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