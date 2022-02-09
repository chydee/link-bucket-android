package com.hoodlums.linkbucket.ui

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.hoodlums.linkbucket.core.constants.BucketConstants.DEF_PACKAGE_ANDROID
import com.hoodlums.linkbucket.core.constants.BucketConstants.DEF_TYPE_DIMEN
import com.hoodlums.linkbucket.core.constants.BucketConstants.RESOURCE_NAME_STATUS_BAR
import kotlin.math.roundToInt

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adjustFontScale(resources.configuration)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    private fun setMarginTopAccordingDisplayCutout(
        context: Context,
        view: View,
        extraTopWithoutCutout: Int,
        extraTopWithCutout: Int
    ) {
        try {
            var statusBarHeight = 0
            val resourceId = context.resources.getIdentifier(
                RESOURCE_NAME_STATUS_BAR,
                DEF_TYPE_DIMEN,
                DEF_PACKAGE_ANDROID
            )
            if (resourceId > 0) {
                statusBarHeight = context.resources.getDimensionPixelSize(resourceId)
            }
            if (statusBarHeight > convertDpToPixel(24f)) {
                val layoutParams = arrayOfNulls<ViewGroup.MarginLayoutParams>(1)
                layoutParams[0] = view.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams[0]!!.topMargin = statusBarHeight + extraTopWithCutout
                view.layoutParams = layoutParams[0]
            } else {
                val layoutParams = arrayOfNulls<ViewGroup.MarginLayoutParams>(1)
                layoutParams[0] = view.layoutParams as ViewGroup.MarginLayoutParams
                layoutParams[0]!!.topMargin = extraTopWithoutCutout
                view.layoutParams = layoutParams[0]
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun convertDpToPixel(dp: Float): Int {
        val metrics: DisplayMetrics = Resources.getSystem().displayMetrics
        val px = dp * (metrics.densityDpi / 160f)
        return px.roundToInt()
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private fun adjustFontScale(configuration: Configuration) {

        if (configuration.fontScale > 1f) {
            configuration.fontScale = 1f

            val metrics = resources.displayMetrics
            val wm = getSystemService(WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getMetrics(metrics)
            metrics.scaledDensity = configuration.fontScale * metrics.density
            baseContext.resources.updateConfiguration(configuration, metrics)
        }
    }

    /**
     * change the status bar color programmatically (and provided the device has Android 5.0)
     * then you can use Window.setStatusBarColor().
     * It shouldn't make a difference whether the activity is derived from Activity or
     * ActionBarActivity.
     */
    private fun setStatusBarColor(color: Int, lightIcons: Boolean) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(color, null)
        } else {
            window.statusBarColor = resources.getColor(color)
        }
        val decorView = getWindow().decorView
        if (lightIcons) {
            // Draw light icons on a dark background color
            decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        } else {
            // Draw dark icons on a light background color
            decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}