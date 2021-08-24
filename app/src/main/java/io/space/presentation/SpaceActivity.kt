package io.space.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.space.presentation.async.Async
import io.space.presentation.theme.SpaceTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<SpaceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceTheme {
                val viewState by viewModel.state.collectAsState()

                AstronomyPictureScreen(viewState) { viewModel.retry() }
            }
        }
    }
}

@Composable
fun AstronomyPictureScreen(viewState: Async<SpaceViewState>, retry: () -> (Unit)) {
    when (viewState) {
        Async.Error -> TODO()
        Async.Loading -> TODO()
        is Async.Success -> TODO()
    }
}
