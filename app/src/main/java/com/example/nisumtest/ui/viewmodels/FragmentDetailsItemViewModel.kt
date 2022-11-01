package com.example.nisumtest.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nisumtest.core.di.utilities.NetworkResult
import com.example.nisumtest.core.models.PokemonDetails
import com.example.nisumtest.usecases.GetPokemonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FragmentDetailsItemViewModel @Inject constructor(
    application: Application,
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase
) : AndroidViewModel(application) {

    private val _getPokemonDetails: MutableLiveData<NetworkResult<PokemonDetails>> = MutableLiveData()
    val getPokemonDetails: LiveData<NetworkResult<PokemonDetails>> = _getPokemonDetails

    fun getPokemonDetails(pokemonId: String) =
        viewModelScope.launch {
            _getPokemonDetails.postValue(NetworkResult.Loading())
            getPokemonDetailsUseCase.invoke(pokemonId).collect { values ->
                _getPokemonDetails.value = values
            }
        }
}