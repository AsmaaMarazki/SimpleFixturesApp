package com.example.fixturesapplication.matches.data.mapper

import com.example.fixturesapplication.matches.data.model.MatchesResponse
import com.example.fixturesapplication.matches.data.model.Score
import com.example.fixturesapplication.matches.domain.model.MatchModel
import com.example.fixturesapplication.matches.domain.model.MatchResult
import com.example.fixturesapplication.matches.domain.model.Winner
import javax.inject.Inject

private const val STATUS_FINISHED = "FINISHED"

class MatchesMapper @Inject constructor() {
    fun mapFrom(matchesResponse: MatchesResponse): List<MatchModel>? {
        return matchesResponse.matches?.run {
            mapNotNull {
                val id = it.id
                val homeTeamName = it.homeTeam?.name
                val awayTeamName = it.awayTeam?.name
                val date = it.utcDate
                if (id != null && date != null && !homeTeamName.isNullOrEmpty() && !awayTeamName.isNullOrEmpty()) {
                    val score = it.score
                    val result = if (it.status == STATUS_FINISHED && score != null) {
                        getMatchResult(score)
                    } else null

                    MatchModel(
                        it.id,
                        homeTeamName,
                        awayTeamName,
                        result,
                        date
                    )
                } else null
            }
        }
    }

    private fun getMatchResult(score: Score): MatchResult? {
        score.fullTime?.let {
            val homeScore = it.homeTeam
            val awayScore = it.awayTeam

            if (homeScore != null && awayScore != null) {
                getWinner(score.winner)?.let { winner ->
                    return MatchResult(winner, homeScore, awayScore)
                }
            }
        }
        return null
    }

    private fun getWinner(winner: String?): Winner? {
        return when (winner) {
            Winner.AWAY_TEAM.type -> Winner.AWAY_TEAM
            Winner.HOME_TEAM.type -> Winner.HOME_TEAM
            Winner.DRAW.type -> Winner.DRAW
            else -> null
        }
    }
}