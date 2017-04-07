package com.treverthomas.summonerssearch.core.dagger;

import android.content.res.Resources;

import com.treverthomas.summonerssearch.home.HomeActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by trever.thomas on 2/18/17.
 */

@PerActivity
@Module
public final class MainActivityModule {

    private final HomeActivity activity;

    public MainActivityModule(final HomeActivity activity) {
        this.activity = activity;
    }

    @PerActivity
    @Provides
    public Resources resources() {
        return activity.getResources();
    }
}
