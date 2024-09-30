package com.example.test.extension

import android.app.Activity
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.widget.Toast
import com.tencent.mmkv.MMKV
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Context.toast(int: Int, length: Int = Toast.LENGTH_SHORT){
    toast(getString(int), length)
}

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    doToast(this@toast, msg, length)
}

private fun doToast(context: Context, msg: String, length: Int) {
    CoroutineScope(Dispatchers.Main).launch {
        if (context is Activity) {
            if (!context.isDestroyed && !context.isFinishing) {
                Toast.makeText(context, msg, length).show()
            }
        } else {
            Toast.makeText(context, msg, length).show()
        }
    }
}

fun <T : Parcelable?> MMKV.encodeListParcelable(key: String, value: List<T>) {
    val byteArray = convertListParcelableToByteArray(value)
    this.encode(key, byteArray)
}

private fun <T : Parcelable?> convertListParcelableToByteArray(list: List<T>): ByteArray {
    val parcel = Parcel.obtain()
    return try {
        parcel.writeList(list)
        parcel.marshall()
    } finally {
        parcel.recycle()
    }
}

inline fun <reified T : Parcelable?> MMKV.decodeListParcelable(
    key: String,
    defaultValue: List<T>? = null
): List<T>? {
    val byteArray = this.decodeBytes(key)
    return convertByteArrayToListParcelable(byteArray, defaultValue)
}

inline fun <reified T : Parcelable?> convertByteArrayToListParcelable(
    byteArray: ByteArray?,
    defaultValue: List<T>? = null
): List<T>? {

    if (byteArray == null) {
        return defaultValue
    }
    val parcel = Parcel.obtain()
    return try {
        parcel.unmarshall(byteArray, 0, byteArray.size)
        parcel.setDataPosition(0)
        val list: ArrayList<T> = ArrayList()
        parcel.readList(list, T::class.java.classLoader)
        list
    } catch (e: Exception) {
        defaultValue
    } finally {
        parcel.recycle()
    }

}