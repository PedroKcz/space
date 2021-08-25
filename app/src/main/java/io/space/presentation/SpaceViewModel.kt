package io.space.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.space.domain.entity.onError
import io.space.domain.entity.onSuccess
import io.space.domain.usecase.AstronomyPictureOfTheDayFetcher
import io.space.presentation.async.Async
import io.space.presentation.async.Async.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpaceViewModel(
    private val fetcher: AstronomyPictureOfTheDayFetcher
) : ViewModel() {

    private val _state = MutableStateFlow<Async<SpaceViewState>?>(null)
    val state: StateFlow<Async<SpaceViewState>?> = _state

    fun fetchPictureOfTheDay() {
        _state.value = Async.Loading

        viewModelScope.launch {
            fetcher.fetch()
                .onSuccess { _state.value = Success(SpaceViewState(it)) }
                .onError { _state.value = Async.Error }
        }
    }
}
