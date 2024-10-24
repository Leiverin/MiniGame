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

        intent.putExtra(
            Intent.EXTRA_TEXT,
            body + "\n\n\n" + "DEVICE INFORMATION (Device information is useful for application improvement and development)" + "\n\n" + getDeviceInfo()
        )

        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                context.getString(R.string.you_need_install_gmail), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun getDeviceInfo(): String {
        val densityText = when (Resources.getSystem().displayMetrics.densityDpi) {
            DisplayMetrics.DENSITY_LOW -> "LDPI"
            DisplayMetrics.DENSITY_MEDIUM -> "MDPI"
            DisplayMetrics.DENSITY_HIGH -> "HDPI"
            DisplayMetrics.DENSITY_XHIGH -> "XHDPI"
            DisplayMetrics.DENSITY_XXHIGH -> "XXHDPI"
            DisplayMetrics.DENSITY_XXXHIGH -> "XXXHDPI"
            else -> "HDPI"
        }

        //TODO: Update android Q
        val stat = StatFs(Environment.getExternalStorageDirectory().path)
        var megAvailable = 0L
        val bytesAvailable: Long
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            bytesAvailable = stat.blockSizeLong * stat.availableBlocksLong
            megAvailable = bytesAvailable / (1024 * 1024)
        }


        return "Manufacturer ${Build.MANUFACTURER}, Model ${Build.MODEL}," + " ${Locale.getDefault()}, " + "osVer ${Build.VERSION.RELEASE}, Screen ${Resources.getSystem().displayMetrics.widthPixels}x${Resources.getSystem().displayMetrics.heightPixels}, " + "$densityText, Free space ${megAvailable}MB, TimeZone ${
            TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT)
        }"
    }
}