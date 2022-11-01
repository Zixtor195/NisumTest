package com.example.nisumtest.ui.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.nisumtest.R
import com.example.nisumtest.core.di.utilities.NetworkResult
import com.example.nisumtest.core.models.PokemonDetails
import com.example.nisumtest.core.models.PokemonListResult
import com.example.nisumtest.databinding.FragmentPokemonDetailsBinding
import com.example.nisumtest.ui.viewmodels.FragmentDetailsItemViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentPokemonDetails : Fragment() {


    private val viewModel: FragmentDetailsItemViewModel by viewModels()
    private lateinit var binding: FragmentPokemonDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPokemonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpObserverGetPokemonDetails()
        getBundleArguments(arguments)
        super.onViewCreated(view, savedInstanceState)
    }


    /** Begin of GetFirstPokemonDetails Functions **/

    private fun setUpObserverGetPokemonDetails() {
        viewModel.getPokemonDetails.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.i("SearchResultsFragment", "GetItemsSearchFromQuery Work on success")
                    response.data?.let { handleGetPokemonDetailsSuccess(it) }
                }
                is NetworkResult.Loading -> {
                    Log.i("SearchResultsFragment", "GetItemsSearchFromQuery Work on loading")
                    handleGetPokemonDetailsLoading()
                }
                is NetworkResult.Error -> {
                    Log.e("SearchResultsFragment", "GetItemsSearchFromQuery Work on error: " + response.message)
                    handleGetPokemonDetailsError()
                }
            }
        }
    }

    private fun handleGetPokemonDetailsSuccess(pokemonDetails: PokemonDetails) {
        binding.progressBarSearchResult.visibility = View.GONE

        pokemonDetails.let {
            if (!it.name.isNullOrBlank() && !it.sprites?.sprite.isNullOrBlank() && it.types?.isNotEmpty() == true) {
                binding.textViewInformative.visibility = View.GONE
                binding.textViewPokemonName.visibility = View.VISIBLE
                binding.appCompatImageViewPokemonSprite.visibility = View.VISIBLE
                binding.textViewPokemonPrincipalType.visibility = View.VISIBLE
                binding.textViewPokemonSecondaryType.visibility = View.VISIBLE
                binding.textViewPokemonName.text = it.name


                binding.textViewPokemonPrincipalType.text = it.types[0].type?.name ?: ""
                if (it.types.size >= 2) {
                    binding.textViewPokemonSecondaryType.text = it.types[1].type?.name ?: ""
                }

                Picasso.get().load(it.sprites?.sprite?.replace("http:", "https:"))
                    .error(R.drawable.ic_launcher_background).into(binding.appCompatImageViewPokemonSprite)


            } else {
                binding.textViewInformative.visibility = View.VISIBLE
                binding.progressBarSearchResult.visibility = View.GONE
                binding.appCompatImageViewPokemonSprite.visibility = View.GONE
                binding.textViewPokemonPrincipalType.visibility = View.GONE
                binding.textViewPokemonSecondaryType.visibility = View.GONE
                binding.textViewInformative.text = getText(R.string.results_no_found)

            }
        }
    }


    private fun handleGetPokemonDetailsLoading() {
        binding.progressBarSearchResult.visibility = View.VISIBLE
        binding.textViewInformative.visibility = View.GONE
        binding.textViewPokemonName.visibility = View.GONE
        binding.textViewPokemonPrincipalType.visibility = View.GONE
        binding.textViewPokemonSecondaryType.visibility = View.GONE
        binding.appCompatImageViewPokemonSprite.visibility = View.GONE
    }

    private fun handleGetPokemonDetailsError() {
        binding.textViewInformative.visibility = View.VISIBLE

        binding.progressBarSearchResult.visibility = View.GONE
        binding.textViewPokemonName.visibility = View.GONE
        binding.appCompatImageViewPokemonSprite.visibility = View.GONE
        binding.textViewPokemonPrincipalType.visibility = View.GONE
        binding.textViewPokemonSecondaryType.visibility = View.GONE
        binding.textViewInformative.text = getText(R.string.results_error)
    }

    /** End of GetFirstPokemonDetails Functions **/


    /** get args and Setup on UI **/

    private fun getBundleArguments(arguments: Bundle?) {
        val args = arguments?.let { FragmentPokemonDetailsArgs.fromBundle(it).pokemonItemListDetails }
        val pokemonID = args?.url?.replace("https://pokeapi.co/api/v2/pokemon", "")?.replace("/", "") ?: ""

        viewModel.getPokemonDetails(pokemonID)

    }


}