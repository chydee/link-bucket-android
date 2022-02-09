package com.hoodlums.linkbucket.ui.utils.extensions

import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun EditText.takeText(): String {
    return this.text.toString().trim()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun ImageView.setImageDrawableWithAnimation(drawable: Drawable, duration: Int = 300) {
    val currentDrawable = getDrawable()
    if (currentDrawable == null) {
        setImageDrawable(drawable)
        return
    }

    val transitionDrawable = TransitionDrawable(
        arrayOf(
            currentDrawable,
            drawable
        )
    )
    setImageDrawable(transitionDrawable)
    transitionDrawable.startTransition(duration)
}

fun View.clickWithDebounce(debounceTime: Long = 1000L, lifecycle: Lifecycle, action: () -> Unit) {
    this.setOnClickListener {
        this@clickWithDebounce.isEnabled = false
        action()
        lifecycle.coroutineScope.launch {
            delay(debounceTime)
            this@clickWithDebounce.isEnabled = true
        }
    }
}

fun View.showSnackBar(message: String) {
    this.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
    }
}

fun View.snackBarWithAction(message: String, actionName: String, action: () -> Unit) {
    this.let {
        Snackbar.make(it, message, Snackbar.LENGTH_LONG)
            .setAction(actionName) {
                action()
            }
            .show()
    }
}

