package com.vald3nir.jetpack_compose.presentation.fragmets.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.presentation.components.ProductHeader
import com.vald3nir.jetpack_compose.presentation.components.ProductImageCarousel
import com.vald3nir.jetpack_compose.presentation.theme.JetpackComposeTheme

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    games: GamesDTO? = null
) {
    if (games == null) return
    val scrollState = rememberScrollState()
    val name = games.name.orEmpty()
    val imageUrl = games.backgroundImage.orEmpty()
    val releaseDate = games.released.orEmpty()
    val description = HtmlCompat
        .fromHtml(games.description.orEmpty(), HtmlCompat.FROM_HTML_MODE_COMPACT)
        .toString()
    val listImageCarousel = mutableListOf<String>()
    games.backgroundImage?.let {
        listImageCarousel.add(it)
    }
    games.backgroundImageAdditional?.let {
        listImageCarousel.add(it)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(scrollState)
    ) {
        ProductHeader(
            modifier = Modifier.padding(16.dp),
            imageUrl = imageUrl,
            name = name,
            releaseDate = releaseDate,
        )
        ProductImageCarousel(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth(),
            listImage = listImageCarousel
        )
        Text(
            text = description,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    JetpackComposeTheme {
        DetailScreen()
    }
}