package com.treverthomas.summonerssearch.home;

import com.treverthomas.summonerssearch.core.dagger.PerActivity;
import com.treverthomas.summonerssearch.core.ui.BaseBindingPresenter;

import javax.inject.Inject;

/**
 * Created by trever.thomas on 2/18/17.
 */

@PerActivity
public final class HomePresenter extends BaseBindingPresenter<HomeViewModel>{

    @Inject
    public HomePresenter() {

    }

    @Override
    protected void load() {
        if (!hasViewModel()) {
            return;
        }
        //load summoner profile
        //load recent games
    }
}
