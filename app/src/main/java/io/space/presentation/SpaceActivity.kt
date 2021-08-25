package io.space.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import io.space.domain.entity.AstronomyPicture
import io.space.presentation.async.Async
import io.space.presentation.error.ErrorScreen
import io.space.presentation.loading.LoadingScreen
import io.space.presentation.theme.SpaceTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModel<SpaceViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchPictureOfTheDay()

        setContent {
            SpaceTheme {
                Surface {
                    val viewState by viewModel.state.collectAsState()

                    viewState?.let { SpaceScene(it) }
                }
            }
        }
    }

    @Composable
    private fun SpaceScene(viewState: Async<SpaceViewState>) = when (viewState) {
        Async.Error -> ErrorScreen { viewModel.fetchPictureOfTheDay() }
        Async.Loading -> LoadingScreen()
        is Async.Success -> AstronomyPictureScreen(viewState.value.data)
    }
}

@Composable
fun AstronomyPictureScreen(content: AstronomyPicture) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberImagePainter(content.url),
            contentDescription = null,
            modifier = Modifier.height(500.dp)
        )
        Text(
            text = content.title,
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )
        Text(
            text = content.description,
            color = Color.White,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun AstronomyPictureScreenPreview() {
    SpaceTheme {
        AstronomyPictureScreen(
            AstronomyPicture(
                title = "Foto batuta",
                description = "descrição da foto",
                url = ""
            )
        )
    }
}
