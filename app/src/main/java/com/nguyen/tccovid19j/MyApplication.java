package com.nguyen.tccovid19j;

import android.app.Application;

public class MyApplication extends Application {
    AppComponent appComponent = DaggerAppComponent.create();
}
