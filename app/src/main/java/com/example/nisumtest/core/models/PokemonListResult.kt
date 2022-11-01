package com.example.nisumtest.core.models

import com.google.gson.annotations.SerializedName

class PokemonListResult {
    @SerializedName("results") var results : List<PokemonItemListDetails> = emptyList()
}