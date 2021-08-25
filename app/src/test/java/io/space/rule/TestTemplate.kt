package io.space.rule

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.junit.Assert

class TestTemplate<T>(private val scope: CoroutineScope) {

    private val emissions = mutableListOf<T>()
    private lateinit var job: Job

    fun observe(target: Flow<T>) {
        job = scope.launch { target.toList(emissions) }
    }

    fun verifyExpectedStates(vararg expectedStates: T) {
        Assert.assertEquals(expectedStates.toList(), emissions)
    }

    fun cancel() = job.cancel()
}
