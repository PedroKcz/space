package io.space

import io.space.domain.entity.Status
import io.space.domain.usecase.AstronomyPictureOfTheDayFetcher
import io.space.fixtures.dummyAstronomyPicture
import io.space.presentation.SpaceViewModel
import io.space.presentation.SpaceViewState
import io.space.presentation.async.Async
import io.space.rule.TestStateRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SpaceViewModelUnitTest {

    @get:Rule
    var rule = TestStateRule<Async<SpaceViewState>?>()

    @Mock
    private lateinit var fetcher: AstronomyPictureOfTheDayFetcher

    private lateinit var viewModel: SpaceViewModel

    @Before
    fun setUp() {
        viewModel = SpaceViewModel(fetcher)

        rule.setSource(viewModel.state)
    }

    @Test
    fun `it should emit success state if pic is fetched`() = rule.testStates {
        whenever(fetcher.fetch()).thenReturn(Status.Success(dummyAstronomyPicture))

        viewModel.fetchPictureOfTheDay()

        verifyExpectedStates(
            null,
            Async.Loading,
            Async.Success(SpaceViewState(dummyAstronomyPicture))
        )
    }

    @Test
    fun `it should retry and emit success state if pic is fetched`() = rule.testStates {
        whenever(fetcher.fetch()).thenReturn(Status.Error)

        viewModel.fetchPictureOfTheDay()

        whenever(fetcher.fetch()).thenReturn(Status.Success(dummyAstronomyPicture))

        viewModel.fetchPictureOfTheDay()

        verifyExpectedStates(
            null,
            Async.Loading,
            Async.Error,
            Async.Loading,
            Async.Success(SpaceViewState(dummyAstronomyPicture))
        )
    }
}
