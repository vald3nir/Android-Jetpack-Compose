package com.vald3nir.jetpack_compose.data.dtos

import com.google.gson.annotations.SerializedName

data class GamesResponseDTO(
    @field:SerializedName("next")
    val next: String? = null,
    @field:SerializedName("previous")
    val previous: String? = null,
    @field:SerializedName("count")
    val count: Int,
    @field:SerializedName("results")
    val results: List<GamesDTO> = listOf(),
)