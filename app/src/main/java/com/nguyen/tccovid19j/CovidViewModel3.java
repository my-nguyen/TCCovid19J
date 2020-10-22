package com.nguyen.tccovid19j;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class CovidViewModel3 extends ViewModel {
    private static final String TAG = "CovidViewModel3";
    private Repository3 repository = new Repository3();

    public LiveData<World> fetchWorld() {
        return repository.fetchWorld();
    }

    public LiveData<Data> fetchCountries(int page) {
        Log.d(TAG, "fetchCountries, page: " + page);
        return repository.fetchCountries(page);
    }
}
