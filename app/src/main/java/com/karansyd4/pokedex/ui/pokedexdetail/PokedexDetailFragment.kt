package com.karansyd4.pokedex.ui.pokedexdetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.karansyd4.pokedex.R
import com.karansyd4.pokedex.data.local.PokedexEntity
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.databinding.FragmentPokedexDetailBinding
import com.karansyd4.pokedex.util.Util.ZERO
import com.karansyd4.pokedex.util.ifLet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexDetailFragment : Fragment() {

    companion object {
        private const val TAG = "PokedexDetailFrag_Kar"
    }

    private lateinit var binding: FragmentPokedexDetailBinding

    private lateinit var viewModel: PokedexViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this).get(PokedexViewModel::class.java)
        viewModel.loadData(
            PokedexEvent.GetPokedexByNumberEvent(
                arguments?.getInt(getString(R.string.arg_pokedex_number)) ?: ZERO
            )
        )
        binding = FragmentPokedexDetailBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        ifLet(data.evolveToName, data.evolveCandy) { (evolveToName, evolveCandy) ->
            evolveLayout.apply {
                titleValueLayout.visibility = View.VISIBLE
                txtTitle.text = evolveToName.toString().replaceFirstChar { it.uppercaseChar() }
                txtValue.text = getString(R.string.candy, evolveCandy)
            }
        }

        if (data.mega) {
            megaEvolveLayout.apply {
                titleValueLayout.visibility = View.VISIBLE
                txtTitle.text = getString(R.string.mega, data.name)
                txtValue.text = data.megaEnergy
            }
        }

        buddyLayout.apply {
            titleValueLayout.visibility = View.VISIBLE
            txtTitle.text = getString(R.string.buddy)
            txtValue.text = getString(R.string.cand_km, data.buddyCandyKm)
        }

        attacksLayout.apply {
            fastAttackLayout.titleValueLayout.visibility = View.VISIBLE
            fastAttackLayout.txtTitle.text = getString(R.string.fast)
            fastAttackLayout.txtValue.text = data.fastMove
            chargedAttackLayout.titleValueLayout.visibility = View.VISIBLE
            chargedAttackLayout.txtTitle.text = getString(R.string.charged)
            chargedAttackLayout.txtValue.text = data.chargedMove
            data.specialMove?.let {
                specialAttackLayout.titleValueLayout.visibility = View.VISIBLE
                specialAttackLayout.txtTitle.text = getString(R.string.special)
                specialAttackLayout.txtValue.text = it
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel
    }

}