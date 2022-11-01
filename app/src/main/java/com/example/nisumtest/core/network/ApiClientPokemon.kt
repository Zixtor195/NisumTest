package com.example.nisumtest.core.network

import com.example.nisumtest.core.models.PokemonDetails
import com.example.nisumtest.core.models.PokemonListResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClientPokemon {

    @GET("pokemon?limit=151")
    suspend fun getFirstGenerationPokemon(): Response<PokemonListResult>


    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") pokemonId: String,
    ): Response<PokemonDetails>

}