package com.example.nisumtest.usecases

import com.example.nisumtest.core.di.utilities.NetworkResult
import com.example.nisumtest.core.models.PokemonDetails
import com.example.nisumtest.data.repositories.interfaces.RepositoryPokemonApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonDetailsUseCase @Inject constructor(private val repositoryPokemonApi: RepositoryPokemonApi) {
    suspend operator fun invoke(pokemonId: String): Flow<NetworkResult<PokemonDetails>> {
        return repositoryPokemonApi.getPokemonDetails(pokemonId)
    }
}