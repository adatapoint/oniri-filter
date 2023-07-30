package com.vince.onirifilter.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.URLUtil
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Activity.setStatusBarColor(@ColorRes color: Int) {
    this.window.run {
        clearFlags(android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
        addFlags(android.view.WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        statusBarColor = ContextCompat.getColor(this@setStatusBarColor, color)
    }
}

fun Context.openInWebBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    } else {
        if (URLUtil.isValidUrl(url)) {
            showToast("Browser not found")
        } else {
            Log.e("ONIRI", IllegalArgumentException("Invalid url: $url").toString())
        }
    }
}
