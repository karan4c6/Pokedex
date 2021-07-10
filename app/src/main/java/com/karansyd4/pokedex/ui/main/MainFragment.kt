package com.karansyd4.pokedex.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.karansyd4.pokedex.R
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        private const val TAG = "MainFragment_Kar"
    }

    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var pokedexAdapter: PokedexAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView: ")
        binding = MainFragmentBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        viewModel.loadData(MainStateEvent.GetPokedexEvents)
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
                    displayLoading(false)
                    displayData(result.data)
                }
                is Result.Error -> {
                    Log.d(TAG, "observePokedexData: ERROR")
                    displayLoading(false)
                    displayError(result.message)
                }
                else -> displayError("Something went wrong")
            }
        })
    }

    private fun displayData(data: List<Pokedex>) {
        Log.d(TAG, "displayData: pokedex list size: ${data.size}")
        pokedexAdapter = PokedexAdapter(getPokedexCards(data))
        binding.pokemonList.apply {
            adapter = pokedexAdapter
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(PokedexItemOffsetDecoration(itemOffset = R.dimen.default_offset))
        }
    }

    private fun getPokedexCards(data: List<Pokedex>) = data.map {
        PokedexCardVO(data = it, onClickListener = ::cardClickListener)
    }

    /**
     * Click Listener for Pokedex Card Item Click
     */
    private fun cardClickListener(pokedexCardVO: PokedexCardVO) {
        Log.d(TAG, "cardClickListener: Number Clicked: ${pokedexCardVO.data.number}")
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayLoading(isLoading: Boolean = true) {
        // todo
    }

}
