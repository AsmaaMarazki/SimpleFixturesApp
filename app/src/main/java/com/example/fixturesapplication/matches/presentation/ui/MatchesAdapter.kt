package com.example.fixturesapplication.matches.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fixturesapplication.databinding.ItemMatchBinding
import com.example.fixturesapplication.matches.presentation.model.MatchViewDataModel

class MatchesAdapter(
    val matches: List<MatchViewDataModel>
) :
    RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>() {

    class MatchesViewHolder(private val binding: ItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchViewDataModel) {
            binding.run {
                tvTeamsNames.text = "${match.homeTeamName} vs ${match.awayTeamName}"
                tvMatchInfo.text = match.gameResult?.let {
                    "Result: ${it.homeScore}:${it.awayScoreInt} for ${it.winnerName ?: "Draw"}"
                } ?: "Time: ${match.time}"

                switchFavourite.setOnCheckedChangeListener { _, fav ->
                    match.isFavourite = fav
                }
                switchFavourite.isChecked = match.isFavourite
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(
            ItemMatchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return matches.size
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(matches[position])
    }
}