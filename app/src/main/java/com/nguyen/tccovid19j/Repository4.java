package com.nguyen.tccovid19j;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class Repository4 {
    private CovidService service;

    @Inject
    Repository4(CovidService service) {
        this.service = service;
    }

    LiveData<World> fetchWorld() {
        MutableLiveData<World> world = new MutableLiveData<>();
        Call<WorldGson> call = service.fetchWorld();
        call.enqueue(new Callback<WorldGson>() {
            @Override
            public void onResponse(Call<WorldGson> call, Response<WorldGson> response) {
                world.setValue(response.body().data);
            }

            @Override
            public void onFailure(Call<WorldGson> call, Throwable t) {
                world.setValue(null);
            }
        });
        return world;
    }

    LiveData<Data> fetCountries(int page) {
        MutableLiveData<Data> data = new MutableLiveData<>();
        Call<Countries> call = service.fetchCountries("total_cases", page);
        call.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                data.setValue(response.body().data);
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
