package com.example.fixturesapplication.matches.data.repository

import com.example.fixturesapplication.matches.data.MatchesApi
import com.example.fixturesapplication.matches.data.mapper.MatchesMapper
import com.example.fixturesapplication.matches.domain.model.MatchModel
import com.example.fixturesapplication.matches.domain.repository.MatchesRepository
import com.example.fixturesapplication.network.NetworkClient
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(private val mapper: MatchesMapper) : MatchesRepository {
    override suspend fun getMatches(): List<MatchModel>? {
        return mapper.mapFrom(
            NetworkClient.createService(MatchesApi::class.java).getMatches()
        )
    }
}