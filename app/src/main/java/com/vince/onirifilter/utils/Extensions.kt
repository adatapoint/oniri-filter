package com.vince.onirifilter.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.URLUtil
import android.widget.Toast

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
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
