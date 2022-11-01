package com.example.nisumtest.core.models.abilities

import com.google.gson.annotations.SerializedName

data class PokemonAbilities(
    @SerializedName("ability") val ability: PokemonAbility?,
    @SerializedName("is_hidden") val isHidden: Boolean?,
    )