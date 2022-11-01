package com.example.nisumtest.ui.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.nisumtest.core.di.SharedPreferences
import com.example.nisumtest.core.di.utilities.NetworkResult
import com.example.nisumtest.core.models.PokemonItemListDetails
import com.example.nisumtest.core.models.PokemonListResult
import com.example.nisumtest.usecases.GetFirstGenerationPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentSearchResultsViewModel @Inject constructor(
    application: Application,
    private val getFirstGenerationPokemonUseCase: GetFirstGenerationPokemonUseCase
) : AndroidViewModel(application) {
    var context = application

    private val _getFirstGenerationPokemonList: MutableLiveData<NetworkResult<PokemonListResult>> = MutableLiveData()
    val getFirstGenerationPokemonList: LiveData<NetworkResult<PokemonListResult>> = _getFirstGenerationPokemonList

    fun getFirstGenerationPokemon() =
        viewModelScope.launch {
            _getFirstGenerationPokemonList.postValue(NetworkResult.Loading())
            getFirstGenerationPokemonUseCase.invoke().collect { values ->
                _getFirstGenerationPokemonList.value = values
            }
        }

    fun savePokemonListOnPreferences(pokemonList: List<PokemonItemListDetails>) {
        SharedPreferences.savePokemonList(pokemonList, context)
    }

    fun getPokemonListFiltered(keyWord: String): List<PokemonItemListDetails> {
        val pokemonList = SharedPreferences.getPokemonList(context)

        return pokemonList.filter { it.name?.contains(keyWord) ?: true }
    }


}