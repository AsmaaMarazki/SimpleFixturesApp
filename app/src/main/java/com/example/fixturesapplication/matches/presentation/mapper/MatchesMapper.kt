package com.example.fixturesapplication.matches.presentation.mapper

import com.example.fixturesapplication.matches.domain.model.MatchModel
import com.example.fixturesapplication.matches.domain.model.Winner
import com.example.fixturesapplication.matches.presentation.model.MatchResult
import com.example.fixturesapplication.matches.presentation.model.MatchViewDataModel
import com.example.fixturesapplication.utils.DateUtils
import java.util.*
import javax.inject.Inject

class MatchesMapper @Inject constructor(private val dateUtils: DateUtils) {
    fun mapFrom(matches: List<MatchModel>): LinkedHashMap<String, List<MatchViewDataModel>> {
        val map = matches.sortedByDescending { it.date }.mapNotNull {
            val homeName = it.homeTeamName
            val awayName = it.awayTeamName

            val matchResult = it.gameResult?.let { result ->
                MatchResult(
                    getWinnerName(
                        homeName,
                        awayName,
                        result.winner
                    ), result.homeScore, result.awayScore
                )

            }
            dateUtils.getDate(DateUtils.Pattern_T_ZONE, it.date)?.let { date ->
                val day = dateUtils.formatDate(DateUtils.PATTERN_YYYY_MM_DD, date).toString()

                MatchViewDataModel(
                    it.id,
                    homeName,
                    awayName,
                    matchResult,
                    day,
                    dateUtils.formatDate(DateUtils.PATTERN_HH_MM, date).toString()
                )

            }
        }.groupBy {
            it.day
        }

        return LinkedHashMap<String, List<MatchViewDataModel>>().apply {
            dateUtils.formatDate(DateUtils.PATTERN_YYYY_MM_DD, Date(System.currentTimeMillis()))
                .toString()
                .let { key ->
                    map.toMutableMap().remove(key)?.let {
                        put(key, it)
                    }
                }

            putAll(map)
        }

    }

    private fun getWinnerName(homeName: String, awayName: String, winner: Winner): String? {
        return when (winner) {
            Winner.HOME_TEAM -> homeName
            Winner.AWAY_TEAM -> awayName
            else -> null
        }

    }
}