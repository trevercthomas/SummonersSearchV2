package com.treverthomas.summonerssearch.core.service.dagger;

import com.treverthomas.summonerssearch.core.service.LeagueApi;
import com.treverthomas.summonerssearch.core.service.LeagueService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module for Providing League Services, Dynamic and Static
 */
@Module
public class ServiceModule {

    private final static String LEAGUE_API = "https://na1.api.riotgames.com/lol/";

    @Provides
    @Singleton
    LeagueService leagueService() {
        final LeagueApi leagueApi = new Retrofit.Builder()
                .baseUrl(LEAGUE_API)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build().create(LeagueApi.class);

        return new LeagueService(leagueApi);
    }
}
