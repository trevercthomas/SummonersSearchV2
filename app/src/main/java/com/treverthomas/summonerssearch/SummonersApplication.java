package com.treverthomas.summonerssearch;

import android.app.Application;

import com.treverthomas.summonerssearch.core.dagger.ApplicationComponent;
import com.treverthomas.summonerssearch.core.dagger.ApplicationModule;
import com.treverthomas.summonerssearch.core.dagger.DaggerApplicationComponent;

import timber.log.Timber;

/**
 * Created by trever.thomas on 2/18/17.
 */

public class SummonersApplication extends Application {

    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        createApplicationComponent();
    }

    private void createApplicationComponent() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent component() {
        return component;
    }
}
