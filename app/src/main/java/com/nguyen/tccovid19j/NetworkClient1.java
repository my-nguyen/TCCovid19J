package com.nguyen.tccovid19j;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

class NetworkClient1 {
    private static final String COVID_BASE_URL = "https://corona-virus-stats.herokuapp.com/api/v1/cases/";

    public static NetworkClient1 getInstance() {
        if (instance == null) {
            instance = new NetworkClient1();
        }
        return instance;
    }

    private static NetworkClient1 instance;

    private AsyncHttpClient client;

    private NetworkClient1() {
        client = new AsyncHttpClient();
    }

    public void fetchWorld(JsonHttpResponseHandler handler) {
        String url = COVID_BASE_URL + "general-stats";
        client.get(url, handler);
    }

    public void fetchCountries(int page, JsonHttpResponseHandler handler) {
        String url = COVID_BASE_URL + "countries-search?" + "order=total_cases" + "&" + "page=" + page;
        client.get(url, handler);
    }
}
