package com.example.nisumtest.data.repositories.interfaces

import com.example.nisumtest.core.di.utilities.NetworkResult
import com.example.nisumtest.core.models.PokemonDetails
import com.example.nisumtest.core.models.PokemonItemListDetails
import com.example.nisumtest.core.models.PokemonListResult
import kotlinx.coroutines.flow.Flow

interface RepositoryPokemonApi {
    suspend fun getFirstGenerationPokemon(): Flow<NetworkResult<PokemonListResult>>
    suspend fun getPokemonDetails(pokemonId: String): Flow<NetworkResult<PokemonDetails>>
}