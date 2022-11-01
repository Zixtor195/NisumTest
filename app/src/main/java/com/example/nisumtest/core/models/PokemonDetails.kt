package com.example.nisumtest.core.models

import com.example.nisumtest.core.models.abilities.PokemonAbilities
import com.example.nisumtest.core.models.moves.PokemonMoves
import com.example.nisumtest.core.models.sprite.PokemonSprites
import com.example.nisumtest.core.models.types.PokemonTypes
import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    @SerializedName("abilities") val abilities: List<PokemonAbilities>?,
    @SerializedName("moves") val moves: List<PokemonMoves>?,
    @SerializedName("types") val types: List<PokemonTypes>?,
    @SerializedName("sprites") val sprites: PokemonSprites?,
    @SerializedName("name") val name: String?,
)