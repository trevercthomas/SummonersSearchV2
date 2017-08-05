package com.treverthomas.summonerssearch.home;

import com.treverthomas.summonerssearch.core.dagger.PerActivity;
import com.treverthomas.summonerssearch.core.ui.BaseBindingPresenter;
import com.treverthomas.summonerssearch.profile.ProfilePresenter;

import javax.inject.Inject;

/**
 * Home View Presenter for loading base summoner data
 */

@PerActivity
public final class HomePresenter extends BaseBindingPresenter<HomeViewModel>{

    private ProfilePresenter profilePresenter;


    /**
     * Base presenter for Homeview
     * @param profilePresenter Presenter provided for loading Profile View
     */
    @Inject
    public HomePresenter(ProfilePresenter profilePresenter) {
        this.profilePresenter = profilePresenter;
    }

    @Override
    public void load() {
        if (!hasViewModel()) {
            return;
        }
        //load summoner profile
        //load recent games
        //Test passing summoner name via load
        profilePresenter.setSummoner("IT WORKS");
    }
}
