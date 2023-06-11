package com.vald3nir.jetpack_compose.presentation.fragmets.detail

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vald3nir.jetpack_compose.R
import com.vald3nir.jetpack_compose.domain.utils.Response
import com.vald3nir.jetpack_compose.presentation.components.*
import com.vald3nir.jetpack_compose.presentation.theme.JetpackComposeTheme
import org.koin.androidx.compose.koinViewModel


@Composable
fun DetailFragment(
    modifier: Modifier = Modifier,
    detailViewModel: DetailViewModel = koinViewModel(),
    id: Int = -1,
) {

    fun launch() {
        detailViewModel.getDetailGames(id)
    }

    launch()
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        when (val gamesResponse = detailViewModel.gamesState.value) {
            is Response.Loading -> {
                LoadingCircular(
                    modifier = Modifier.fillMaxWidth()
                )
            }

            is Response.Success -> {
                DetailScreen(
                    games = gamesResponse.data
                )
            }

            is Response.Failure -> {
                ErrorButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.error_message),
                    onClick = {
                        launch()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailFragmentPreview() {
    JetpackComposeTheme {
        DetailFragment()
    }
}