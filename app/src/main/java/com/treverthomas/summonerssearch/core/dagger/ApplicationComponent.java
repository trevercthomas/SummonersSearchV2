package com.treverthomas.summonerssearch.core.dagger;

import android.content.Context;

import com.treverthomas.summonerssearch.SummonersApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by trever.thomas on 2/18/17.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class
})
public interface ApplicationComponent {

    Context context();

    void inject(SummonersApplication application);
}
