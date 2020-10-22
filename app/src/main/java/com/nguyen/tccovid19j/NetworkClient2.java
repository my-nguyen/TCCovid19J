package com.nguyen.tccovid19j;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class NetworkClient2 {
    private static final String COVID_BASE_URL = "https://corona-virus-stats.herokuapp.com/api/v1/cases/";

    public static NetworkClient2 getInstance() {
        if (instance == null) {
            instance = new NetworkClient2();
        }
        return instance;
    }

    private static NetworkClient2 instance;

    public CovidService service;

    private NetworkClient2() {
        Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(COVID_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(CovidService.class);
    }

    public void fetchWorld(Callback<WorldGson> callback) {
        service.fetchWorld().enqueue(callback);
    }

    public void fetchCountries(int page, Callback<Countries> callback) {
        service.fetchCountries("total_cases", page).enqueue(callback);
    }
}
