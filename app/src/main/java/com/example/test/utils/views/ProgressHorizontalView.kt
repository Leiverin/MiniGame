package com.example.test.utils.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.test.R

class ProgressHorizontalView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private var max = 100L
    private var progress = 0L

    private val paintBg = Paint().apply {
        color = ContextCompat.getColor(context, R.color.white)
        style = Paint.Style.FILL
    }
    private val paintProgress = Paint().apply {
        color = ContextCompat.getColor(context, R.color.color_progress)
        style = Paint.Style.FILL
    }

    private val rectBg = RectF()
    private val rectProgress = RectF()

    fun setMax(value: Long){
        this.max = value
    }

    fun setProgress(progress: Long){
        this.progress = progress
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawProgress(canvas)
    }

    private fun drawProgress(canvas: Canvas) {
        val progress = progress.toFloat() * canvas.width / max
        rectProgress.set(0f, 0f, progress, canvas.height.toFloat())
        canvas.drawRoundRect(rectProgress, canvas.height / 2f, canvas.height / 2f, paintProgress)
    }

    private fun drawBackground(canvas: Canvas) {
        rectBg.set(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat())
        canvas.drawRoundRect(rectBg, canvas.height / 2f, canvas.height / 2f, paintBg)
    }

}