package com.treverthomas.summonerssearch.profile;

import com.treverthomas.summonerssearch.core.dagger.PerActivity;
import com.treverthomas.summonerssearch.core.ui.BaseBindingPresenter;

import javax.inject.Inject;

/**
 * Profile presenter for retrieving summoner profile data
 */

@PerActivity
public class ProfilePresenter  extends BaseBindingPresenter<ProfileView.ProfileViewModel> {

    private String summonerName;
    private ProfileView.ProfileViewModel viewModel;

    @Inject
    public ProfilePresenter() {

    }

    @Override
    public void load() {
        if (!hasViewModel()) {
            return;
        }

        //This is just for testing purposes
        viewModel = getViewModel();
        viewModel.summonerName.set(summonerName);

    }

    /**
     * Summoner passed through to load more Profile Data
     * @param summoner
     */
    public void setSummoner(String summoner) {
        summonerName = summoner;
        load();
    }
}
