package com.example.fixturesapplication.matches.presentation

import com.example.fixturesapplication.matches.data.mapper.MatchesMapper
import com.example.fixturesapplication.matches.data.repository.MatchesRepositoryImpl
import com.example.fixturesapplication.matches.domain.repository.MatchesRepository
import com.example.fixturesapplication.matches.domain.usecase.GetMatchesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class MatchesModule {
    @Provides
    fun providesMatchesRepository(mapper: MatchesMapper): MatchesRepository =
        MatchesRepositoryImpl(mapper)

    @Provides
    fun providesUseCaseDispatcher(
    ): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providesMatchesUseCase(
        repository: MatchesRepository,
        dispatcher: CoroutineDispatcher
    ): GetMatchesUseCase =
        GetMatchesUseCase(repository, dispatcher)
}