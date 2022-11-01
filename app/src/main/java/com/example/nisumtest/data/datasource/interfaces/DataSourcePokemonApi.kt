package com.example.nisumtest.data.datasource.interfaces

import com.example.nisumtest.core.models.PokemonDetails
import com.example.nisumtest.core.models.PokemonListResult
import retrofit2.Response

interface DataSourcePokemonApi {
    suspend fun getFirstGenerationPokemon(): Response<PokemonListResult>
    suspend fun getPokemonDetails(pokemonId : String): Response<PokemonDetails>
}