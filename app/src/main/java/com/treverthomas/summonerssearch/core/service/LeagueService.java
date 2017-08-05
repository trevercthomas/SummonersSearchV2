package com.treverthomas.summonerssearch.core.service;

import com.treverthomas.summonerssearch.core.model.Summoner;

import io.reactivex.Observable;

/**
 * Service for access to the Dynamic LeagueAPI
 */

public class LeagueService {

    private final LeagueApi leagueApi;

    public LeagueService(final LeagueApi leagueApi) {
        this.leagueApi = leagueApi;
    }

    public Observable<Summoner> getSummonerByName(final String name, final String apiKey) {
        return leagueApi.getSummonerByName(name, apiKey).toObservable();
    }
}
