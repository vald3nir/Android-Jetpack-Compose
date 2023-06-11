package com.vald3nir.jetpack_compose.data.networking

import com.vald3nir.jetpack_compose.data.dtos.GamesDTO
import com.vald3nir.jetpack_compose.data.dtos.GamesResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesService {

    @GET("games")
    suspend fun getAllGames(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
    ): GamesResponseDTO

    @GET("games/{id}")
    suspend fun getGamesDetail(
        @Path("id") id: Int,
        @Query("key") key: String,
    ): GamesDTO
}