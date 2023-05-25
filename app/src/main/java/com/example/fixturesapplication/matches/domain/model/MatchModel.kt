package com.example.fixturesapplication.matches.domain.model


data class MatchModel(
    val id: Int,
    val homeTeamName: String,
    val awayTeamName: String,
    val gameResult: MatchResult? = null,
    val date: String,
)

data class MatchResult(val winner: Winner, val homeScore: Int = 0, val awayScore: Int = 0)

enum class Winner(val type: String) {
    AWAY_TEAM("AWAY_TEAM"), HOME_TEAM("HOME_TEAM"), DRAW("DRAW")
}