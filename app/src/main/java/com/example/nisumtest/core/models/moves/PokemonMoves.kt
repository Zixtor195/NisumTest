package com.example.nisumtest.core.models.moves

import com.google.gson.annotations.SerializedName

data class PokemonMoves(
    @SerializedName("move") val move: PokemonMove?,
)