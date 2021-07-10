package com.karansyd4.pokedex.util

import android.content.Context
import android.os.SystemClock
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.karansyd4.pokedex.R

// https://stackoverflow.com/questions/35513636/multiple-variable-let-in-kotlin
inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit): Unit? {
    return if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    } else null
}

fun Int.isEven() = this % 2 == 0

fun View.clickWithDebounce(debounceTime: Long = 1200L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun View.dp2px(dp: Int) = resources.displayMetrics.density * dp

fun View.dp2px(dp: Float) = resources.displayMetrics.density * dp

fun Context.isTablet(): Boolean = resources.getBoolean(R.bool.isTablet)

fun Context.isFontScaled() =
    this.resources.configuration.fontScale >= ResourcesCompat.getFloat(this.resources, R.dimen.font_scale_threshold)