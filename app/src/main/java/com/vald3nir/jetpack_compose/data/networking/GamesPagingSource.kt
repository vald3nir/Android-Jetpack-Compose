package com.vald3nir.jetpack_compose.data.networking

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.data.dtos.GamesResponseDTO

class GamesPagingSource(
    private val response: suspend (Int) -> GamesResponseDTO,
) : PagingSource<Int, GamesDTO>() {

    override fun getRefreshKey(state: PagingState<Int, GamesDTO>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GamesDTO> {
        return try {
            val nextPage = params.key ?: 1
            val gamesList = response.invoke(nextPage)
            LoadResult.Page(
                data = gamesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = gamesList
                    .next
                    ?.substringAfter("page=")
                    ?.substringBefore("&")
                    ?.toInt() ?: nextPage
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}