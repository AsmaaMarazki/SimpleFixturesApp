package com.example.fixturesapplication.matches.data.model

import com.google.gson.annotations.SerializedName

data class MatchesResponse(
    val filters: Filters? = null,
    val resultSet: ResultSet? = null,
    val competition: Competition? = null,
    val matches: List<Match>? = null
)

data class Filters(
    val season: String? = null,
    @SerializedName("matchday") var matchDay: String? = null
)

data class ResultSet(
    val count: Int? = null,
    val first: String? = null,
    val last: String? = null,
    val played: Int? = null
)

data class Competition(
    val id: Int? = null,
    val name: String? = null,
    val code: String? = null,
    val type: String? = null,
    @SerializedName("emblem") val emblem: String? = null
)

data class Area(
    val id: Int? = null,
    val name: String? = null,
    val code: String? = null,
    val flag: String? = null
)

data class Season(
    val id: Int? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    @SerializedName("currentMatchday") val currentMatchDay: Int? = null,
    val winner: String? = null,
    val stages: ArrayList<String>? = null
)

data class Coach(
    val id: Int? = null,
    val name: String? = null,
    val nationality: String? = null
)

data class TeamDetails(
    val id: Int? = null,
    val name: String? = null,
    val shortName: String? = null,
    val tla: String? = null,
    val crest: String? = null,
    val coach: Coach? = Coach(),
    val leagueRank: Int? = null,
    val formation: String? = null,
    val lineup: ArrayList<String>? = null,
    val bench: ArrayList<String>? = null
)

data class Identity(
    val id: Int? = null,
    val name: String? = null
)

data class Score(
    val winner: String? = null,
    val duration: String? = null,
    val fullTime: MatchTime? = null,
    val halfTime: MatchTime? = null,
    val extraTime: MatchTime? = null,
    val penalties: Penalties? = null
)

data class MatchTime(
    val homeTeam: Int? = null,
    val awayTeam: Int? = null
)

data class Goals(
    val minute: Int? = null,
    val injuryTime: String? = null,
    val type: String? = null,
    val team: Identity? = null,
    val scorer: Identity? = null,
    val assist: String? = null,
    val score: Score? = null
)


data class Penalties(
    val player: Identity? = null,
    val team: Identity? = null,
    val scored: Boolean? = null
)

data class Odds(
    val homeWin: Double? = null,
    val draw: Double? = null,
    val awayWin: Double? = null
)

data class Referees(
    val id: Int? = null,
    val name: String? = null,
    val type: String? = null,
    val nationality: String? = null
)

data class Match(
    val area: Area? = null,
    val competition: Competition? = null,
    val season: Season? = null,
    val id: Int? = null,
    val utcDate: String? = null,
    val status: String? = null,
    val minute: String? = null,
    val injuryTime: Int? = null,
    val attendance: String? = null,
    val venue: String? = null,
    @SerializedName("matchday") var matchDay: Int? = null,
    val stage: String? = null,
    val group: String? = null,
    val lastUpdated: String? = null,
    val homeTeam: TeamDetails? = null,
    val awayTeam: TeamDetails? = null,
    val score: Score? = null,
    val goals: ArrayList<Goals>? = null,
    val penalties: ArrayList<Penalties>? = null,
    val bookings: ArrayList<String>? = null,
    val substitutions: ArrayList<String>? = null,
    val odds: Odds? = null,
    val referees: ArrayList<Referees>? = null
)