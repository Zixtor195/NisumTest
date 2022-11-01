package com.example.nisumtest.core.models.moves

import com.google.gson.annotations.SerializedName

data class PokemonMove(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
)