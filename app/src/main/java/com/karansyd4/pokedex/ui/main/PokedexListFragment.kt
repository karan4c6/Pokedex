package com.karansyd4.pokedex.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.karansyd4.pokedex.R
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.databinding.FragmentPokedexListBinding
import com.karansyd4.pokedex.util.PokedexHelper.getPokedexCards
import com.karansyd4.pokedex.util.navigateWithAnim

class PokedexListFragment : Fragment() {

    companion object {
        fun newInstance() = PokedexListFragment()
        private const val TAG = "PokedexListFragment_Kar"
    }

    private lateinit var binding: FragmentPokedexListBinding

    private lateinit var viewModel: PokedexViewModel

    private lateinit var pokedexAdapter: PokedexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        binding = FragmentPokedexListBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        viewModel = ViewModelProvider(requireActivity()).get(PokedexViewModel::class.java)
        viewModel.loadData(PokedexEvent.GetPokedexEvent)
        observePokedexData()
    }

    private fun observePokedexData() {
        viewModel.pokedexData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    Log.d(TAG, "observePokedexData: LOADING")
                    displayLoading()
                }
                is Result.Success<List<Pokedex>> -> {
                    Log.d(TAG, "observePokedexData: Success")
                    displayData(result.data)
                }
                is Result.Error -> {
                    Log.d(TAG, "observePokedexData: ERROR")
                    displayError(result.message)
                }
                else -> displayError("Something went wrong")
            }
        })
    }

    private fun displayData(data: List<Pokedex>) {
        binding.pokemonList.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
        Log.d(TAG, "displayData: pokedex list size: ${data.size}")
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
