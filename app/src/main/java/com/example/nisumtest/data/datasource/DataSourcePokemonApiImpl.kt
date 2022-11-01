package com.example.nisumtest.data.datasource

import com.example.nisumtest.core.models.PokemonDetails
import com.example.nisumtest.core.network.ApiClientPokemon
import com.example.nisumtest.data.datasource.interfaces.DataSourcePokemonApi
import retrofit2.Response
import javax.inject.Inject

class DataSourcePokemonApiImpl @Inject constructor(private val apiClientPokemon: ApiClientPokemon) :
    DataSourcePokemonApi {

    override suspend fun getFirstGenerationPokemon() = apiClientPokemon.getFirstGenerationPokemon()

    override suspend fun getPokemonDetails(pokemonId: String) = apiClientPokemon.getPokemonDetails(pokemonId)
}

