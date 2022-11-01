package com.example.nisumtest.ui.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nisumtest.R
import com.example.nisumtest.core.di.utilities.NetworkResult
import com.example.nisumtest.core.models.PokemonItemListDetails
import com.example.nisumtest.core.models.PokemonListResult
import com.example.nisumtest.databinding.FragmentPokemonListBinding
import com.example.nisumtest.ui.adapters.PokemonListAdapter
import com.example.nisumtest.ui.viewmodels.FragmentSearchResultsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FragmentPokemonList : Fragment(), PokemonListAdapter.SearchResultListener {


    private val viewModel: FragmentSearchResultsViewModel by viewModels()
    private lateinit var binding: FragmentPokemonListBinding
    private lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpAppBar()
        getFirstGenerationPokemonList()
        setUpRecyclerPokemonList()
        setUpObserverGetPokemonList()
        super.onViewCreated(view, savedInstanceState)
    }

    /** Begin of AppBar Functions **/
    private fun setUpAppBar() {
        with(binding.widgetSearchbar) {
            inputTextSearchText.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    val pokemonList = viewModel.getPokemonListFiltered(textView.text.toString())
                    pokemonListAdapter.updateItems(pokemonList)

                    hideKeyboard(requireContext(), inputTextSearchText)
                    true
                } else {
                    false
                }
            }
        }
    }

    private fun hideKeyboard(context: Context, view: View) {
        val imm: InputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /** End of AppBar Functions **/

    /** Begin of GetFirstGenerationPokemonList Functions **/

    private fun setUpRecyclerPokemonList() {
        with(binding.recyclerViewPokemonList) {
            pokemonListAdapter = PokemonListAdapter(this@FragmentPokemonList)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = pokemonListAdapter
        }
    }

    private fun getFirstGenerationPokemonList() {
        viewModel.getFirstGenerationPokemon()
    }

    private fun setUpObserverGetPokemonList() {
        viewModel.getFirstGenerationPokemonList.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    Log.i("SearchResultsFragment", "GetItemsSearchFromQuery Work on success")
                    response.data?.let { handleGetPokemonListSuccess(it) }
                }
                is NetworkResult.Loading -> {
                    Log.i("SearchResultsFragment", "GetItemsSearchFromQuery Work on loading")
                    handleGetPokemonListLoading()
                }
                is NetworkResult.Error -> {
                    Log.e("SearchResultsFragment", "GetItemsSearchFromQuery Work on error: " + response.message)
                    handleGetPokemonListError()
                }
            }
        }
    }

    private fun handleGetPokemonListSuccess(pokemonListResult: PokemonListResult) {
        binding.progressBarSearchResult.visibility = View.GONE

        if (pokemonListResult.results.isNotEmpty()) {
            binding.textViewInformative.visibility = View.GONE
            binding.recyclerViewPokemonList.visibility = View.VISIBLE

            viewModel.savePokemonListOnPreferences(pokemonListResult.results)
            pokemonListAdapter.updateItems(pokemonListResult.results)

        } else {
            binding.textViewInformative.visibility = View.VISIBLE
            binding.recyclerViewPokemonList.visibility = View.GONE
            binding.textViewInformative.text = getText(R.string.results_no_found)
        }
    }

    private fun handleGetPokemonListLoading() {
        binding.progressBarSearchResult.visibility = View.VISIBLE
        binding.textViewInformative.visibility = View.GONE
        binding.recyclerViewPokemonList.visibility = View.GONE
    }

    private fun handleGetPokemonListError() {
        binding.progressBarSearchResult.visibility = View.GONE
        binding.textViewInformative.visibility = View.VISIBLE
        binding.recyclerViewPokemonList.visibility = View.GONE
        binding.textViewInformative.text = getText(R.string.results_error)
    }

    /** End of GetFirstGenerationPokemonList Functions **/

    /** Begin of OnClickListeners **/
    override fun onClickPokemonItem(pokemonItemListDetails: PokemonItemListDetails) {
        val navController = findNavController()
        val navAction = FragmentPokemonListDirections
            .actionNavigationFragmentPokemonListToNavigationFragmentPokemonDetails(pokemonItemListDetails)
        navController.navigate(navAction)
    }

    /** End of OnClickListeners **/
}