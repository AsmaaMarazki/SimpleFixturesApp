package com.example.fixturesapplication.matches.data.mapper

import com.example.fixturesapplication.matches.data.model.Match
import com.example.fixturesapplication.matches.data.model.MatchesResponse
import com.example.fixturesapplication.matches.data.model.TeamDetails
import com.example.fixturesapplication.matches.domain.model.MatchModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MatchesMapperTest {
    private lateinit var mapper: MatchesMapper

    @Before
    fun setup() {
        mapper = MatchesMapper()
    }

    @Test
    fun mapFrom() {
        val input = getValidResponse()
        val actual = mapper.mapFrom(input)
        val expected = getValidMatches()

        Assert.assertEquals(actual, expected)
    }
    private fun getValidResponse() = MatchesResponse(matches = arrayListOf<Match>().apply {
        add(
            Match(
                id = 0,
                homeTeam = TeamDetails(name = "home"),
                awayTeam = TeamDetails(name = "away"),
                utcDate = "2023-5-30"
            )
        )
        add(
            Match(
                id = 1,
                homeTeam = TeamDetails(name = "home1"),
                awayTeam = TeamDetails(name = "away1"),
                utcDate = "2023-5-30"
            )
        )
        add(
            Match(
                id = 2,
                homeTeam = TeamDetails(name = "home2"),
                awayTeam = TeamDetails(name = "away2"),
                utcDate = "2023-5-3"
            )
        )
    })
    private fun getValidMatches() = arrayListOf<MatchModel>().apply {
        add(MatchModel(id = 0, "home", "away", null, "2023-5-30"))
        add(MatchModel(id = 1, "home1", "away1", null, "2023-5-30"))
        add(MatchModel(id = 2, "home2", "away2", null, "2023-5-3"))
    }
}