package com.example.fixturesapplication.matches.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.fixturesapplication.R
import com.example.fixturesapplication.databinding.FragmentMatchesBinding
import com.example.fixturesapplication.matches.presentation.viewmodel.MatchesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MatchesFragment : Fragment() {
    private var _binding: FragmentMatchesBinding? = null
    private val binding get() = _binding!!

    private val matchesViewModel by activityViewModels<MatchesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMatches()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        collectMatchesLoading()
        collectMatchesError()
        collectMatches()
    }

    private fun initClickListener() {
        binding.btnOpenFavourites.setOnClickListener {
            findNavController().navigate(R.id.action_matchesFragment_to_favouritesFragment)
        }
    }

    private fun getMatches() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                matchesViewModel.getMatches()
            }
        }
    }

    private fun collectMatchesLoading() {
        lifecycleScope.launch {
            matchesViewModel.loadingStateFlow.collect {
                if (it) {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun collectMatches() {
        lifecycleScope.launch {
            matchesViewModel.matchesFlow.collect { map ->
                binding.rvMatchGroups.adapter = MatchesGroupAdapter(map)
            }
        }
    }

    private fun collectMatchesError() {
        lifecycleScope.launch {
            matchesViewModel.matchesErrorSharedFlow.collect {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

}