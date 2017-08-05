package com.treverthomas.summonerssearch.core.service;

import com.treverthomas.summonerssearch.core.model.Summoner;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Declaration of Dynamic League data points
 */

public interface LeagueApi {

    @GET("summoner/v3/summoners/by-name/{name}")
    Single<Summoner> getSummonerByName(@Path("name") String name,
                                       @Query("api_key") String apiKey);
}
