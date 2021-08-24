package com.karansyd4.pokedex.ui.compose.library.tel

import android.util.Log
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karansyd4.pokedex.theme.PokedexTheme

/**
 * LowEmphasis ->
 *      Focused - 2dp neutralForegroundActive color border + transparent- #00000000
 *      Pressed - transparent - #00000000
 *      Clicked -
 *      Enabled -
 *      Disabled -
 *          Text size- 16sp, textStyle- Normal
 *            <item android:color="@color/interactiveForegroundActive" android:state_pressed="true" />
 *            <item android:color="@color/interactiveForegroundActive" android:state_focused="true" />
 *            <item android:color="@color/interactiveForegroundDisabled" android:state_enabled="false" />
 *            <item android:color="@color/interactiveForegroundNormal" />
 *
 * Medium Emphasis ->
 *
 * HighEmphasis ->
 *
 */
@Composable
fun LowActionButton(text: String, onClick: () -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val isPressed by interactionSource.collectIsPressedAsState()
    val buttonText = remember { text }

    val interactions = remember { mutableStateListOf<Interaction>() }
    val clickable = Modifier.clickable(
        interactionSource = interactionSource,
        indication = null
    ) { /* update some business state here */ }


    Log.d("LowActionButton", "LowActionButton: isFocused:$isFocused")
    Log.d("LowActionButton", "LowActionButton: isPressed:$isPressed")

    val focusedModifier = Modifier
        .wrapContentSize()
        .defaultMinSize(48.dp)
        .border(
            width = 2.dp,
            color = Color.Black,
            shape = RectangleShape
        )
        .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp)
        .indication(interactionSource = interactionSource, indication = LocalIndication.current)

    TextButton(
        onClick = onClick,
        modifier = focusedModifier.then(clickable)
    ) {
        val pressed = interactions.any { it is PressInteraction.Press }
        val focused = interactions.any { it is FocusInteraction.Focus }

        Text(
            text = if (pressed) "Pressed" else if (focused) "Focused" else buttonText,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            lineHeight = 1.sp
        )
    }
}

// Preview Methods below

@Preview(showBackground = true)
@Composable
fun PreviewActionButton() {
    PokedexTheme {
        LowActionButton(text = "Action Button") {}
    }
}