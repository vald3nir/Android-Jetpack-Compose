package com.vald3nir.jetpack_compose.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.vald3nir.jetpack_compose.R
import com.vald3nir.jetpack_compose.presentation.theme.JetpackComposeTheme

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductImageCarousel(
    modifier: Modifier = Modifier,
    listImage: List<String> = listOf()
) {
    val state = rememberPagerState()
    HorizontalPager(
        state = state,
        count = listImage.size,
        modifier = modifier
    ) { pagerScope ->
        val imagePainter = rememberAsyncImagePainter(
            model = listImage[pagerScope],
            error = painterResource(id = R.drawable.ic_launcher_foreground),
        )
        Box(contentAlignment = Alignment.BottomCenter) {
            Image(
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxSize(),
                painter = imagePainter,
                contentDescription = listImage[pagerScope],
                contentScale = ContentScale.Crop,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductImageCarouselPreview() {
    JetpackComposeTheme {
        ProductImageCarousel()
    }
}