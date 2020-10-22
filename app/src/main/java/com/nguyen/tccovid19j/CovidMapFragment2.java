package com.nguyen.tccovid19j;

import android.util.Log;
import android.view.View;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidMapFragment2 extends CovidMapFragment {
    private static final String TAG = "CovidMapFragment2";

    @Override
    void getAllCountries() {
        CountriesAdapter adapter = CountriesAdapter.getInstance(getContext());
        binding.progressBar.setVisibility(View.VISIBLE);
        // binding.covidMap.setVisibility(View.GONE);

        if (adapter.totalPages != 0 && adapter.currentPage == adapter.totalPages) {
            // all countries have been pre-fetched, just load the map
            loadMap();
        } else {
            NetworkClient2.getInstance().fetchCountries(adapter.currentPage + 1, new Callback<Countries>() {
                @Override
                public void onResponse(Call<Countries> call, Response<Countries> response) {
                    Countries countries = response.body();
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

                @Override
                public void onFailure(Call<Countries> call, Throwable t) {
                    Log.d(TAG, "onFailure");
                }
            });
        }
    }
}