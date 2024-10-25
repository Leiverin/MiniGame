package com.example.test.utils

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.StrictMode
import android.util.DisplayMetrics
import android.widget.Toast
import com.example.test.R
import java.util.Locale
import java.util.TimeZone

object Constants {
    const val ASSET_DIRECTORY = "file:///android_asset/levels/"


    private fun disableExposure() {
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                val m = StrictMode::class.java.getMethod("disableDeathOnFileUriExposure")
                m.invoke(null)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendEmailMoree(
        context: Context, addresses: Array<String>, subject: String, body: String
    ) {

        disableExposure()
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                context.getString(R.string.you_need_install_gmail), Toast.LENGTH_SHORT
            ).show()
        }
    }

}