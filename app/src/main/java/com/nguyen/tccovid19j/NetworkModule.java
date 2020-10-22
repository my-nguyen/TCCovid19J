package com.nguyen.tccovid19j;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private static final String COVID_BASE_URL = "https://corona-virus-stats.herokuapp.com/api/v1/cases/";

    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    Retrofit provideRetrofit(GsonConverterFactory factory) {
        return new Retrofit.Builder()
                .baseUrl(COVID_BASE_URL)
                .addConverterFactory(factory.create())
                .build();
    }

    @Provides
    CovidService provideCovidService(Retrofit retrofit) {
        return retrofit.create(CovidService.class);
    }
}
