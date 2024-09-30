package com.example.test.extension

import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.test.R
import com.example.test.databinding.DialogResultBinding

private var dialogResult: Dialog? = null

fun Context.showDialogResult(text: String){
    this?.let {
        dialogResult = Dialog(this)
        val binding = DialogResultBinding.inflate(LayoutInflater.from(this))
        dialogResult?.apply {
            setContentView(binding.root)
            setLayout(1f)
            binding.tvResult.text = text
            binding.btnAgain.setPreventDoubleClick {
                dismissDialogLoading()
            }
            setCancelable(false)
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }
    }
}

fun Context?.dismissDialogLoading() {
    dialogResult?.dismiss()
    dialogResult = null
}

fun Dialog?.setLayout(mValue: Float){
    this?.let {
        val screenWith = Resources.getSystem().displayMetrics.widthPixels
        val params = window?.attributes
        params?.width = (screenWith * mValue).toInt()
        params?.height = WindowManager.LayoutParams.MATCH_PARENT
        window?.attributes = params
    }
}