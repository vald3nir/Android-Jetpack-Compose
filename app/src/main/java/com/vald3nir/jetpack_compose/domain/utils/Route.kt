package com.vald3nir.jetpack_compose.domain.utils

import com.vald3nir.jetpack_compose.domain.utils.Const.DETAIL_ARG_GAMES_ID
import com.vald3nir.jetpack_compose.domain.utils.Const.DETAIL_SCREEN
import com.vald3nir.jetpack_compose.domain.utils.Const.HOME_SCREEN


sealed class Route(val route: String) {

    object Home : Route(HOME_SCREEN)

    object Detail : Route("$DETAIL_SCREEN/{$DETAIL_ARG_GAMES_ID}") {
        fun createRoute(gamesId: Int) = "$DETAIL_SCREEN/$gamesId"
    }
}