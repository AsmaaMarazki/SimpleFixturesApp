package com.example.fixturesapplication.matches.domain.usecase

import com.example.fixturesapplication.matches.domain.model.MatchModel
import com.example.fixturesapplication.matches.domain.repository.MatchesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetMatchesUseCase(
    private val matchesRepository: MatchesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun execute(): List<MatchModel>? {
        return withContext(dispatcher) {
            matchesRepository.getMatches()
        }
    }
}