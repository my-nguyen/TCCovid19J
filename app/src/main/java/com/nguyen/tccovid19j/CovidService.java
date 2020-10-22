package com.nguyen.tccovid19j;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface CovidService {
    @GET("general-stats")
    Call<WorldGson> fetchWorld();

    @GET("countries-search")
    Call<Countries> fetchCountries(@Query("order") String order, @Query("page") int page);
}
