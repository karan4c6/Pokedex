package com.karansyd4.pokedex.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.karansyd4.pokedex.R
import com.karansyd4.pokedex.data.model.Pokedex
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.databinding.PokedexDetailBinding
import com.karansyd4.pokedex.util.Util.ZERO

class PokedexDetailFragment : Fragment() {

    companion object {
        fun newInstance() = PokedexDetailFragment()
        private const val TAG = "PokedexDetailFrag_Kar"
        private const val ARG_POKEDEX_NUMBER = "pokedexNumber"
    }

    private lateinit var binding: PokedexDetailBinding

    private lateinit var viewModel: PokedexViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
        binding = PokedexDetailBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PokedexViewModel::class.java)
        viewModel.loadPokedexEntryForNumber(arguments?.getInt(ARG_POKEDEX_NUMBER) ?: ZERO)
        observePokedexDetailData()
    }

    private fun observePokedexDetailData() {
        viewModel.pokedexDetail.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    displayData(result.data)
                }
                is Result.NetworkError -> {
                    displayData(result.cacheData, fromCache = true)
                }
                is Result.Loading -> {
                    displayLoading()
                }
                is Result.Error -> {
                    displayError(message = result.message)
                }
                else -> Unit
            }
        }
    }

    private fun displayLoading() {
        Log.d(TAG, "display Loading")
    }

    private fun displayError(message: String) {
        Log.e(TAG, "displayError: $message")
    }

    private fun displayData(data: Pokedex, fromCache: Boolean = false) = with(binding) {
        txtName.text = data.name
        txtNumber.text = getString(R.string.pokedex_number_format, data.number)
        txtType.text = data.type.toTypedArray().joinToString("/")
    }
}