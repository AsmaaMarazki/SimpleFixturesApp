package com.example.fixturesapplication.matches.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fixturesapplication.matches.domain.usecase.GetMatchesUseCase
import com.example.fixturesapplication.matches.presentation.mapper.MatchesMapper
import com.example.fixturesapplication.matches.presentation.model.MatchViewDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchesViewModel @Inject constructor(
    private val matchesMapper: MatchesMapper, private val getMatchesUseCase: GetMatchesUseCase
) : ViewModel() {

    private val _matchesSharedFlow =
        MutableSharedFlow<LinkedHashMap<String, List<MatchViewDataModel>>>(
            replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    val matchesFlow: Flow<LinkedHashMap<String, List<MatchViewDataModel>>> =
        _matchesSharedFlow.distinctUntilChanged()


    private val _matchesErrorSharedFlow = MutableSharedFlow<String>()
    val matchesErrorSharedFlow: SharedFlow<String> = _matchesErrorSharedFlow

    private val _loadingStateFlow = MutableStateFlow(true)
    val loadingStateFlow: StateFlow<Boolean> = _loadingStateFlow

    val favouritesFlow by lazy {
        matchesFlow.map { map ->
            map.values.flatMap { matches ->
                matches.filter {
                    it.isFavourite
                }
            }
        }
    }

    fun getMatches() {
        viewModelScope.launch {
            try {
                val result = getMatchesUseCase.execute()
                if (result.isNullOrEmpty()) {
                    _matchesErrorSharedFlow.emit("No matches found")
                } else {
                    _matchesSharedFlow.emit(matchesMapper.mapFrom(result))
                }
                _loadingStateFlow.emit(false)
            } catch (e: Exception) {
                _matchesErrorSharedFlow.emit("Something wend wrong")
                _loadingStateFlow.emit(false)
            }
        }
    }

}