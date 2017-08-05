package com.treverthomas.summonerssearch.home;

import android.annotation.SuppressLint;

import com.treverthomas.summonerssearch.core.dagger.PerActivity;
import com.treverthomas.summonerssearch.core.model.Summoner;
import com.treverthomas.summonerssearch.core.service.LeagueService;
import com.treverthomas.summonerssearch.core.ui.BaseBindingPresenter;
import com.treverthomas.summonerssearch.profile.ProfilePresenter;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Home View Presenter for loading base summoner data
 */

@PerActivity
public final class HomePresenter extends BaseBindingPresenter<HomeViewModel>{

    private ProfilePresenter profilePresenter;
    private LeagueService leagueService;


    /**
     * Base presenter for Homeview
     * @param profilePresenter Presenter provided for loading Profile View
     */
    @Inject
    public HomePresenter(ProfilePresenter profilePresenter, LeagueService service) {
        this.profilePresenter = profilePresenter;
        this.leagueService = service;
    }

    @SuppressLint("RxLeakedSubscription")
    @Override
    protected void load() {
        if (!hasViewModel()) {
            return;
        }
        //load summoner profile
        //load recent games
        //Test passing summoner name via load
        profilePresenter.setSummoner("IT WORKS");

        leagueService.getSummonerByName("Anonymist", "RGAPI-902657b8-8459-4a24-8069-032027a1e1e0")
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribeWith(new Observer<Summoner>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull Summoner summoner) {
                        Timber.d("Summoner: " + summoner);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
