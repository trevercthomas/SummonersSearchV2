package com.treverthomas.summonerssearch.core.dagger;

import com.treverthomas.summonerssearch.home.HomeActivity;
import com.treverthomas.summonerssearch.home.HomeView;

import dagger.Component;

/**
 * Created by trever.thomas on 2/18/17.
 */

@PerActivity
@Component(dependencies = {ApplicationComponent.class},
        modules = {MainActivityModule.class})
public interface MainActivityComponent {

    void inject(HomeActivity mainActivity);

    void inject(HomeView homeView);
}
