package com.example.fixturesapplication.matches.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fixturesapplication.databinding.ItemFavouriteMatchBinding
import com.example.fixturesapplication.matches.presentation.model.MatchViewDataModel

class FavouriteMatchesAdapter(val matches: List<MatchViewDataModel>) :
    RecyclerView.Adapter<FavouriteMatchesAdapter.MatchesViewHolder>() {

    class MatchesViewHolder(private val binding: ItemFavouriteMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: MatchViewDataModel) {
            binding.run {
                tvTeamsNames.text = "${match.homeTeamName} vs ${match.awayTeamName}"
                tvMatchInfo.text = match.gameResult?.let {
                    "Result: ${it.homeScore}:${it.awayScoreInt} for ${it.winnerName ?: "Draw"}"
                } ?: "Time: ${match.time}"

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        return MatchesViewHolder(
            ItemFavouriteMatchBinding.inflate(
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