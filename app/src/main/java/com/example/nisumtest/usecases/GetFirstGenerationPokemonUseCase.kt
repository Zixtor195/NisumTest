package com.example.nisumtest.usecases

import com.example.nisumtest.core.di.utilities.NetworkResult
import com.example.nisumtest.core.models.PokemonItemListDetails
import com.example.nisumtest.core.models.PokemonListResult
import com.example.nisumtest.data.repositories.interfaces.RepositoryPokemonApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFirstGenerationPokemonUseCase @Inject constructor(private val repositoryPokemonApi: RepositoryPokemonApi) {
    suspend operator fun invoke(): Flow<NetworkResult<PokemonListResult>> {
        return repositoryPokemonApi.getFirstGenerationPokemon()
    }
}


