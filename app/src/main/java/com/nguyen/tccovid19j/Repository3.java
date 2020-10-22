package com.nguyen.tccovid19j;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class Repository3 {
    private static final String TAG = "Repository3";

    LiveData<World> fetchWorld() {
        MutableLiveData<World> world = new MutableLiveData<>();
        NetworkClient2.getInstance().fetchWorld(new Callback<WorldGson>() {
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

    LiveData<Data> fetchCountries(int page) {
        Log.d(TAG, "fetchCountries, page: " + page);
        MutableLiveData<Data> data = new MutableLiveData<>();
        NetworkClient2.getInstance().fetchCountries(page, new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                data.setValue(response.body().data);
                Log.d(TAG, "fetchCountries, data pagination current page: " + response.body().data.pagination.currentPage);
            }

            @Override
            public void onFailure(Call<Countries> call, Throwable t) {
                data.setValue(null);
                Log.d(TAG, "onFailure");
            }
        });
        return data;
    }
}
