package com.example.fixturesapplication.matches.presentation.model

data class MatchViewDataModel(
    val id: Int,
    val homeTeamName: String,
    val awayTeamName: String,
    val gameResult: MatchResult? = null,
    val day: String,
    val time: String,
    var isFavourite: Boolean = false
)


data class MatchResult(val winnerName: String?, val homeScore: Int = 0, val awayScoreInt: Int = 0)
