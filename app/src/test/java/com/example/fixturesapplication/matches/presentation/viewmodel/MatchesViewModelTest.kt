package com.example.fixturesapplication.matches.presentation.viewmodel

import app.cash.turbine.test
import com.example.fixturesapplication.MainCoroutineDispatcherRule
import com.example.fixturesapplication.matches.domain.model.MatchModel
import com.example.fixturesapplication.matches.domain.model.MatchResult
import com.example.fixturesapplication.matches.domain.model.Winner
import com.example.fixturesapplication.matches.domain.usecase.GetMatchesUseCase
import com.example.fixturesapplication.matches.presentation.mapper.MatchesMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MatchesViewModelTest {

    @get:Rule
    val rule = MainCoroutineDispatcherRule()

    @Mock
    lateinit var getMatchesUseCase: GetMatchesUseCase

    @Mock
    lateinit var matchesMapper: MatchesMapper

    private lateinit var matchesViewModel: MatchesViewModel

    @Before
    fun setup() {
        matchesViewModel = MatchesViewModel(matchesMapper, getMatchesUseCase)
    }

    @Test
    fun `getMatches() - valid params and success data - expected success with map of grouped matches`() {
        runTest {
            val matches = getValidMatches()
            val groupOfMatches = getMapOfMatches()
            //arrange
            doAnswer {
                matches
            }.`when`(getMatchesUseCase).execute()

            doAnswer {
                groupOfMatches
            }.`when`(matchesMapper).mapFrom(matches)

            //assert
            matchesViewModel.loadingStateFlow.test {
                assertEquals(true, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            //act
            matchesViewModel.getMatches()
            advanceUntilIdle()

            //assert
            verify(getMatchesUseCase, times(1)).execute()
            verify(matchesMapper, times(1)).mapFrom(matches)

            matchesViewModel.loadingStateFlow.test {
                assertEquals(false, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            matchesViewModel.matchesFlow.test {
                assertEquals(groupOfMatches, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }


        }
    }

    @Test
    fun `getMatches() - valid params and error occur - expected error`() {
        runTest {
            //arrange
            whenever(getMatchesUseCase.execute()).thenThrow(RuntimeException())

            //assert
            matchesViewModel.loadingStateFlow.test {
                assertEquals(true, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }

            matchesViewModel.matchesErrorSharedFlow.test {
                //act
                matchesViewModel.getMatches()
                advanceUntilIdle()
                //assert
                assertEquals("Something wend wrong", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }


            //assert
            verify(getMatchesUseCase, times(1)).execute()
            verifyNoInteractions(matchesMapper)

            matchesViewModel.loadingStateFlow.test {
                assertEquals(false, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }


        }
    }

    private fun getValidMatches() = mutableListOf<MatchModel>().apply {
        add(MatchModel(1, "home1", "away1", null, "2023-5-30"))
        add(
            MatchModel(
                1,
                "home2",
                "away2",
                MatchResult(Winner.AWAY_TEAM, 0, 2),
                "2023-5-30"
            )
        )
        add(MatchModel(1, "home3", "away3", null, "2023-5-3"))

    }

    private fun getMapOfMatches() = LinkedHashMap<String, List<MatchModel>>().apply {
        val group1 = mutableListOf<MatchModel>().apply {
            add(MatchModel(1, "home1", "away1", null, "2023-5-30"))
            add(
                MatchModel(
                    1,
                    "home2",
                    "away2",
                    MatchResult(Winner.AWAY_TEAM, 0, 2),
                    "2023-5-30"
                )
            )
        }
        val group2 = mutableListOf<MatchModel>().apply {
            add(MatchModel(1, "home3", "away3", null, "2023-5-3"))
        }
        put("2023-5-30", group1)
        put("2023-5-3", group2)
    }
}