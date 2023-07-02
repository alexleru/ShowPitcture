package com.alexleru.showpictrure.presentation.view.customView

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.content.withStyledAttributes

private const val ANIMATION_DURATION = 1000L

class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = common.uikit.R.attr.viewStyleCustomProgressBarViewDefault,
    defStyleRes: Int = 0
) : View(
    context, attrs, defStyleAttr, defStyleRes
) {

    private var progressValue = 0
        set(value) {
            field = value
            invalidate()
        }
    private val paint = Paint().apply {
        isAntiAlias = true
        color = Color.BLUE
        style = Paint.Style.FILL_AND_STROKE
    }

    init {
        getContext().withStyledAttributes(
            attrs,
            common.uikit.R.styleable.ProgressView,
            defStyleAttr,
            defStyleRes
        ) {
            getColor(common.uikit.R.styleable.ProgressView_colorProgress, Color.BLACK).run {
                paint.color = this
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(
            0f,
            0f,
            width.toFloat() * progressValue / 100,
            height.toFloat(),
            paint
        )
        Log.d("animationProgress+++", progressValue.toString())
    }

    fun animationProgress(current: Int) {
        ValueAnimator.ofFloat(progressValue.toFloat(), current.toFloat()).apply {
            interpolator = DecelerateInterpolator()
            duration = ANIMATION_DURATION
            setCurrentFraction(0.01f)

            addUpdateListener {
                (it.animatedValue as Float).let { value ->
                    progressValue = value.toInt()
                }
            }
        }.start()
    }
}