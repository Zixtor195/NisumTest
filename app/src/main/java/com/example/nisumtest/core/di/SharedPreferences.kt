package com.example.nisumtest.core.di

import android.content.Context
import android.content.SharedPreferences
import com.example.nisumtest.core.models.PokemonItemListDetails
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferences {

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("data", Context.MODE_PRIVATE)
    }

    fun savePokemonList(pokemonList: List<PokemonItemListDetails>, context: Context) {
        val jsonPokemonList = Gson().toJson(pokemonList)
        getSharedPreferences(context).edit().putString("pokemonList", jsonPokemonList).apply()
    }

    fun getPokemonList(context: Context): List<PokemonItemListDetails> {
        val pokemonList = getSharedPreferences(context).getString("pokemonList", "")

        return if (pokemonList.isNullOrEmpty()) {
            emptyList()
        } else {
            val itemType = object : TypeToken<List<PokemonItemListDetails>>() {}.type
            Gson().fromJson(pokemonList, itemType)
        }
    }
}
