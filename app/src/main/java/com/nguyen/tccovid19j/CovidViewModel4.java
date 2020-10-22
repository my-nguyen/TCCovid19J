package com.nguyen.tccovid19j;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

class CovidViewModel4 extends ViewModel {
    private Repository4 repository;

    @Inject
    CovidViewModel4(Repository4 repository) {
        this.repository = repository;
    }

    LiveData<World> fetchWorld() {
        return repository.fetchWorld();
    }

    LiveData<Data> fetchCountries(int page) {
        return repository.fetCountries(page);
    }
}
