package com.example.nisumtest.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nisumtest.core.models.PokemonItemListDetails
import com.example.nisumtest.databinding.ItemlistPokemonListBinding

class PokemonListAdapter(private val listener: SearchResultListener) :
    RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private var pokemonDetails: List<PokemonItemListDetails> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemlistPokemonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val team = pokemonDetails[position]
        holder.bind(team)
    }

    override fun getItemCount() = pokemonDetails.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(pokemonDetails: List<PokemonItemListDetails>) {
        this.pokemonDetails = pokemonDetails
        this.notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemlistPokemonListBinding,
        private val listener: SearchResultListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonItemListDetails: PokemonItemListDetails) {
            binding.textViewItemTitle.text = pokemonItemListDetails.name

            /*binding.textViewItemPrice.text = price
            Picasso.get().load(pokemonItemList.thumbnail?.replace("http:", "https:"))
                .error(R.drawable.ic_launcher_background).into(binding.imageViewItem)*/

            binding.cardViewItem.setOnClickListener {
                listener.onClickPokemonItem(pokemonItemListDetails)
            }
        }
    }

    interface SearchResultListener {
        fun onClickPokemonItem(pokemonItemListDetails: PokemonItemListDetails)
    }

}