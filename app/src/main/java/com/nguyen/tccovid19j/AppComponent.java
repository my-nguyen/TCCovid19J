package com.nguyen.tccovid19j;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
interface AppComponent {
    void inject(TotalStatsFragment4 fragment);
    void inject(RegionStatsFragment4 fragment);
    void inject(CovidMapFragment4 fragment);
}
