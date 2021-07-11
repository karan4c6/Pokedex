package com.karansyd4.pokedex.util

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import androidx.annotation.IdRes
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
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

fun NavController.navigateWithAnim(@IdRes destId: Int, args: Bundle? = null) {
    try {
        this.navigate(
            destId,
            args,
            NavOptions.Builder()
                .setEnterAnim(R.anim.slide_anim_in)
                .setExitAnim(R.anim.slide_anim_out)
                .setPopEnterAnim(R.anim.slide_pop_anim_in)
                .setPopExitAnim(R.anim.slide_pop_anim_out).build()
        )
    } catch (e: Exception) {
        Log.e(this::class.java.simpleName, e.message ?: "Error while navigating to $destId")
    }
}