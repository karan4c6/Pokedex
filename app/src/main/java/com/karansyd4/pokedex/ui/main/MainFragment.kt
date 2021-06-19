package com.karansyd4.pokedex.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.setStateEvent(MainStateEvent.GetPokedexEvents)
        observePokedexData()
    }

    private fun observePokedexData() {
        viewModel.pokedexData.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Result.Loading -> {
                    Log.d(TAG, "observePokedexData: LOADING")
                    displayLoading(true)
                }
                is Result.Success<List<Pokedex>> -> {
                    Log.d(TAG, "observePokedexData: Success")
                    displayLoading(false)
                    displayData(result.data)
                }
                is Result.Error -> {
                    Log.d(TAG, "observePokedexData: ERROR")
                    displayLoading(false)
                    displayError(result.exception.message)
                }
                else -> displayError("Something went wrong")
            }
        })
    }

    private fun displayData(data: List<Pokedex>) {
        Log.d(TAG, "displayData: pokedex list size: ${data.size}")
        data.forEach {
            Log.d(TAG, "displayData: \t${it.number}:${it.name} - ${it.type}")
        }
    }

    private fun displayError(message: String?) {
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Unknown error", Toast.LENGTH_LONG).show()
        }
    }

    private fun displayLoading(isLoading: Boolean) {
//        swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun populateRecyclerView(pokedexList: List<Pokedex>) {
//        if (!pokedexList.isNullOrEmpty()) adapter.setItems(ArrayList(pokedexList))
    }

    private fun setupRecyclerView() {
/*
        adapter = BlogAdapter(this)
        blog_recyclerview.layoutManager = LinearLayoutManager(this)
        blog_recyclerview.adapter = adapter
*/
    }
}
