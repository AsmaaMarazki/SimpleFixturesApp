package com.example.fixturesapplication.matches.data

import com.example.fixturesapplication.matches.data.model.MatchesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MatchesApi {
    @GET("competitions/2021/matches")
    suspend fun getMatches(
    ): MatchesResponse
}