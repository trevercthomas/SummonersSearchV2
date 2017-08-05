package com.treverthomas.summonerssearch.core.dagger;

import android.content.Context;

import com.treverthomas.summonerssearch.SummonersApplication;
import com.treverthomas.summonerssearch.core.service.LeagueService;
import com.treverthomas.summonerssearch.core.service.dagger.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by trever.thomas on 2/18/17.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ServiceModule.class
})
public interface ApplicationComponent {

    Context context();

    LeagueService leagueService();

    void inject(SummonersApplication application);
}
