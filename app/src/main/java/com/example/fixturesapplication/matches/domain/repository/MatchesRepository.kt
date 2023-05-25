package com.example.fixturesapplication.matches.domain.repository

import com.example.fixturesapplication.matches.domain.model.MatchModel

interface MatchesRepository {
    suspend fun getMatches(): List<MatchModel>?
}