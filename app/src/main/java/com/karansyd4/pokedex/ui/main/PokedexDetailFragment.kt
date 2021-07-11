package com.karansyd4.pokedex.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.karansyd4.pokedex.R
import com.karansyd4.pokedex.data.local.PokedexEntity
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.databinding.FragmentPokedexDetailBinding
import com.karansyd4.pokedex.util.Util.ZERO

class PokedexDetailFragment : Fragment() {

    companion object {
        private const val TAG = "PokedexDetailFrag_Kar"
    }

    private lateinit var binding: FragmentPokedexDetailBinding

    private lateinit var viewModel: PokedexViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView: ")
        binding = FragmentPokedexDetailBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(PokedexViewModel::class.java)
        observePokedexDetailData()
       /* requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d(TAG, "handleOnBackPressed: ")
                Navigation.findNavController(view).navigate(R.id.action_pokedexDetailFragment_to_pokedexListFragment)
            }
        })*/
    }

    private fun observePokedexDetailData() {
        viewModel.pokedexDetail.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    displayLoading()
                }
                is Result.Success -> {
                    displayData(result.data)
                }
                is Result.NetworkError -> {
                    displayData(result.cacheData, fromCache = true)
                }
                is Result.Error -> {
                    displayError(message = result.message)
                }
                else -> Unit
            }
        }
        viewModel.loadData(
            PokedexEvent.GetPokedexByNumberEvent(
                arguments?.getInt(getString(R.string.arg_pokedex_number)) ?: ZERO
            )
        )
    }

    private fun displayLoading() {
        Log.d(TAG, "display Loading")
    }

    private fun displayError(message: String) {
        Log.e(TAG, "displayError: $message")
    }

    private fun displayData(data: PokedexEntity, fromCache: Boolean = false) = with(binding) {
        Log.d(TAG, "displayData: ${data.number}")
        if (fromCache) {
            Log.d(TAG, "displayData: data displayed from cache")
        }

        txtName.text = data.name
        txtNumber.text = getString(R.string.pokedex_number_format, data.number)
        txtType.text = data.type.joinToString(" / ")
    }

}