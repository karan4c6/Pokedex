package com.karansyd4.pokedex.ui.pokedexlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.karansyd4.pokedex.R
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.PokemonCardData
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.databinding.FragmentPokedexListBinding
import com.karansyd4.pokedex.theme.PokedexTheme
import com.karansyd4.pokedex.ui.compose.library.PokedexGridList
import com.karansyd4.pokedex.ui.pokedexdetail.PokedexEvent
import com.karansyd4.pokedex.ui.pokedexdetail.PokedexViewModel
import com.karansyd4.pokedex.util.PokedexHelper.getPokedexCards
import com.karansyd4.pokedex.util.navigateWithAnim
import com.karansyd4.pokedex.util.padPokedexNumber
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class PokedexListFragment : Fragment() {

    companion object {
        private const val TAG = "PokedexListFragment_Kar"
    }

    private lateinit var binding: FragmentPokedexListBinding

    private val viewModel: PokedexViewModel by viewModels()

    private lateinit var pokedexAdapter: PokedexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokedexListBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this).get(PokedexViewModel::class.java)
        viewModel.loadData(PokedexEvent.GetPokedexEvent)
        observePokedexData()
    }

    private fun observePokedexData() {
        viewModel.pokedexData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    displayLoading()
                }
                is Result.Success<List<Pokedex>> -> {
//                    displayData(result.data)
                    displayComposeView(result.data)
                }
                is Result.Error -> {
                    displayError(result.message)
                }
                else -> displayError("Something went wrong")
            }
        })
    }

    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    private fun displayComposeView(data: List<Pokedex>) {
        binding.progressBar.visibility = View.GONE
        binding.composeListView.setContent {
            PokedexTheme {
                PokedexGridList(pokedexList = getPokedexList(data), onClick = ::onCardClick)
            }
        }
    }

    private fun onCardClick() {
        Log.d(TAG, "onCardClick: navigate to Detail Screen")
    }

    private fun getPokedexList(data: List<Pokedex>): List<PokemonCardData> = data.map {
        PokemonCardData(imageUrl = it.imageUrl, pokedexNumber = it.number.padPokedexNumber(), name = it.name)
    }

    private fun displayData(data: List<Pokedex>) {
        binding.pokemonList.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        pokedexAdapter = PokedexAdapter(getPokedexCards(data, ::cardClickListener))
        binding.pokemonList.apply {
            adapter = pokedexAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(PokedexItemOffsetDecoration(itemOffset = R.dimen.default_offset))
        }
    }

    /**
     * Click Listener for Pokedex Card Item Click
     */
    private fun cardClickListener(pokedexCardVO: PokedexCardVO) {
        Log.d(TAG, "cardClickListener: Number Clicked: ${pokedexCardVO.data.number}")
        findNavController().navigateWithAnim(
            R.id.action_pokedexListFragment_to_pokedexDetailFragment,
            bundleOf(getString(R.string.arg_pokedex_number) to pokedexCardVO.data.number)
        )
    }

    private fun displayError(message: String?) {
        binding.progressBar.visibility = View.GONE
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayLoading() = with(binding) {
        pokemonList.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }
}
