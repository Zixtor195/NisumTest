package com.example.nisumtest.data.repositories

import com.example.nisumtest.core.di.utilities.BaseApiResponse
import com.example.nisumtest.core.di.utilities.NetworkResult
import com.example.nisumtest.core.models.PokemonDetails
import com.example.nisumtest.core.models.PokemonItemListDetails
import com.example.nisumtest.core.models.PokemonListResult
import com.example.nisumtest.data.datasource.interfaces.DataSourcePokemonApi
import com.example.nisumtest.data.repositories.interfaces.RepositoryPokemonApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoryPokemonApiImpl @Inject constructor(private val dataSourcePokemonApi: DataSourcePokemonApi) :
    RepositoryPokemonApi, BaseApiResponse() {

    override suspend fun getFirstGenerationPokemon(): Flow<NetworkResult<PokemonListResult>> {
        return flow {
            emit(safeApiCall { dataSourcePokemonApi.getFirstGenerationPokemon() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getPokemonDetails(pokemonId: String): Flow<NetworkResult<PokemonDetails>> {
        return flow {
            emit(safeApiCall { dataSourcePokemonApi.getPokemonDetails(pokemonId) })
        }.flowOn(Dispatchers.IO)
    }
}