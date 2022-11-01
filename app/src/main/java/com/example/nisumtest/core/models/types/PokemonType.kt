package com.example.nisumtest.core.models.types

import com.google.gson.annotations.SerializedName

data class PokemonType(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
)
