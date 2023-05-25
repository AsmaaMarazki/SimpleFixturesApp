package com.example.fixturesapplication.matches.domain.usecase

import com.example.fixturesapplication.matches.data.model.Match
import com.example.fixturesapplication.matches.data.model.MatchesResponse
import com.example.fixturesapplication.matches.data.model.TeamDetails
import com.example.fixturesapplication.matches.domain.model.MatchModel
import com.example.fixturesapplication.matches.domain.repository.MatchesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetMatchesUseCaseTest {
    @Mock
    lateinit var matchesRepository: MatchesRepository

    private lateinit var useCase: GetMatchesUseCase

    @Before
    fun setUp() {
        useCase = GetMatchesUseCase(matchesRepository, Dispatchers.IO)
    }

    @Test
    fun `getMatches() - empty matches - expected null`() {
        //arrange
        val expectedResponse: List<MatchModel> = emptyList()
        var actual: List<MatchModel>? = null

        //act
        runTest {
            doAnswer {
                expectedResponse
            }.whenever(matchesRepository).getMatches()
            actual = useCase.execute()
        }

        //assert
        Assert.assertEquals(actual, expectedResponse)
    }

    @Test
    fun `getMatches() - has matches - expected success and list of matches`() {
        //arrange
        val expectedResponse: List<MatchModel> = getValidMatches()
        var actual: List<MatchModel>? = null

        //act
        runTest {
            doAnswer {
                expectedResponse
            }.whenever(matchesRepository).getMatches()
            actual = useCase.execute()
        }

        //assert
        Assert.assertEquals(actual, expectedResponse)
    }


    private fun getValidMatches() = arrayListOf<MatchModel>().apply {
        add(MatchModel(id = 0, "home", "away", null, "2023-5-30"))
        add(MatchModel(id = 1, "home1", "away1", null, "2023-5-30"))
        add(MatchModel(id = 2, "home2", "away2", null, "2023-5-3"))
    }
}