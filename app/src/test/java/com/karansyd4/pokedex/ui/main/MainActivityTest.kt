package com.karansyd4.pokedex.ui.main

import androidx.navigation.findNavController
import com.karansyd4.pokedex.R
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [30])
class MainActivityTest {

    private lateinit var activity: MainActivity

    @Before
    @Throws(java.lang.Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun testActivity() {
        assertNotNull(activity)
        // Pokedex List Fragment should be displayed.
        assertEquals(
            R.id.pokedexListFragment,
            activity.findNavController(R.id.nav_host_fragment).currentDestination?.id
        )
    }

    @After
    fun tearDown() {
    }
}