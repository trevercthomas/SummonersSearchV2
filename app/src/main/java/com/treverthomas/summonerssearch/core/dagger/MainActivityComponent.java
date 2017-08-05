package com.treverthomas.summonerssearch.core.dagger;

import com.treverthomas.summonerssearch.home.MainActivity;
import com.treverthomas.summonerssearch.home.HomeView;
import com.treverthomas.summonerssearch.profile.ProfileView;

import dagger.Component;

/**
 * Created by trever.thomas on 2/18/17.
 */

@PerActivity
@Component(dependencies = {ApplicationComponent.class},
        modules = {MainActivityModule.class})
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(HomeView homeView);

    void inject(ProfileView profileView);
}
