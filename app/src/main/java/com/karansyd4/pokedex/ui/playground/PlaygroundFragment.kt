package com.karansyd4.pokedex.ui.playground

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.karansyd4.pokedex.databinding.FragmentPlaygroundLayoutBinding
import com.karansyd4.pokedex.theme.PokedexTheme
import com.karansyd4.pokedex.ui.compose.library.tel.LowActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaygroundFragment : Fragment() {

    companion object {
        private const val TAG = "PlaygroundFragment_Kar"
    }

    @VisibleForTesting
    lateinit var binding: FragmentPlaygroundLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPlaygroundLayoutBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            PokedexTheme {
                ComposeUIScreen()
            }
        }
    }
}

@Composable
fun ComposeUIScreen() {
    MaterialTheme {
        Column {
            Text(text = "Compose UI", modifier = Modifier.padding(16.dp))
            LowActionButton(text = "Low Emphasis Button", onClick = ::onclick)
        }
    }
}

fun onclick() {
    Log.d("PlaygroundUI", "onclick: Button clicked")
}

@Preview(showSystemUi = true)
@Composable
fun PreviewComposeUIScreen() {
    ComposeUIScreen()
}