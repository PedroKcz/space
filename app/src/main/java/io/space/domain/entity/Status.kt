package io.space.domain.entity

sealed class Status<out T> {
    data class Success<T>(val response: T) : Status<T>()
    object Error : Status<Nothing>()
}

suspend fun <T> Status<T>.onError(lambda: suspend () -> Unit): Status<T> {
    if (this is Status.Error) lambda.invoke()
    return this
}

suspend fun <T> Status<T>.onSuccess(lambda: suspend (T) -> Unit): Status<T> {
    if (this is Status.Success) lambda.invoke(this.response)
    return this
}
