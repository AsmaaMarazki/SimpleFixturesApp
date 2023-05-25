package com.example.fixturesapplication.matches.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.fixturesapplication.databinding.ItemMatchGroupBinding
import com.example.fixturesapplication.matches.presentation.model.MatchViewDataModel

class MatchesGroupAdapter(
     val matchGroups: LinkedHashMap<String, List<MatchViewDataModel>>
) :
    RecyclerView.Adapter<MatchesGroupAdapter.MatchesGroupsViewHolder>() {

    class MatchesGroupsViewHolder(private val binding: ItemMatchGroupBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(day: String, matches: List<MatchViewDataModel>) {
            binding.run {
                rvMatches.adapter = MatchesAdapter(matches)
                tvMatchDay.text = day
                btnShowAndHide.setOnCheckedChangeListener { _, show ->
                    rvMatches.isVisible = show
                }
                btnShowAndHide.isChecked = false

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesGroupsViewHolder {
        return MatchesGroupsViewHolder(
            ItemMatchGroupBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return matchGroups.size
    }

    override fun onBindViewHolder(holder: MatchesGroupsViewHolder, position: Int) {
        val group = matchGroups.entries.toList()[position]
        holder.bind(group.key, group.value)
    }
}