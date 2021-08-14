package com.karansyd4.pokedex.ui.pokedexlist

import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.karansyd4.pokedex.PokedexTestData.getPokedexData
import com.karansyd4.pokedex.R
import com.karansyd4.pokedex.data.model.Result
import com.karansyd4.pokedex.ui.main.MainActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowToast

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class PokedexListFragmentTest {

    private lateinit var fragment: PokedexListFragment
    private lateinit var activity: MainActivity

    private fun startFragment(fragment: Fragment) {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()

        assertNotNull(activity)

        val inflater = activity.binding.navHostFragment.findNavController().navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.pokedexListFragment
        activity.binding.navHostFragment.findNavController().graph = graph
        activity.supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragment).commitNow()
    }

    @Before
    fun setUp() {
        fragment = PokedexListFragment()
        startFragment(fragment)
    }

    @Test
    fun testFragmentViewStates() {
        assertNotNull(fragment)
        with(fragment) {
            // Loading State
            (viewModel.pokedexData as MutableLiveData).value = Result.Loading
            assertTrue(binding.progressBar.isVisible)
            assertFalse(binding.pokemonList.isVisible)

            println("${getPokedexData()}")

            // Loaded State
            (viewModel.pokedexData as MutableLiveData).value = Result.Success(data = getPokedexData().pokedex)
            assertFalse(binding.progressBar.isVisible)
            assertTrue(binding.pokemonList.isVisible)
            assertEquals(6, binding.pokemonList.adapter!!.itemCount)

            val adapter = (binding.pokemonList.adapter as PokedexAdapter)
            val viewHolder = adapter.createViewHolder(FrameLayout(context!!), 1)

            for (i in 0 until 6) {
                adapter.onBindViewHolder(viewHolder, i)
                assertEquals("00${i + 1}", viewHolder.pokedexItemBinding.pokedexNumber.text)
            }

            // Failed State
            (viewModel.pokedexData as MutableLiveData).value = Result.Error(message = "Network Error")
            assertFalse(binding.progressBar.isVisible)
            assertEquals("Network Error", ShadowToast.getTextOfLatestToast())
        }
    }
}