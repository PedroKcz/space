package io.space.presentation.async

sealed class Async<out T> {
    object Loading : Async<Nothing>()
    object Error : Async<Nothing>()
    data class Success<T>(val value: T) : Async<T>()
}
