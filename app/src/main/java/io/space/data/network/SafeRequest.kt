package io.space.data.network

import io.space.domain.entity.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SafeRequest {

    suspend operator fun <T> invoke(lambda: suspend () -> T) = withContext(Dispatchers.IO) {
        try {
            Status.Success(lambda.invoke())
        } catch (throwable: Throwable) {
            Status.Error
        }
    }
}
