package com.alexleru.showpitcture.presentation.view.cumstomeView

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
import com.alexleru.showpitcture.R

private const val ANIMATION_DURATION = 1000L

class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
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
            R.styleable.ProgressView,
            defStyleAttr,
            defStyleRes
        ) {
            getColor(R.styleable.ProgressView_colorProgress, Color.BLACK).run {
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

    // TODO: я бы назвал setProgress или что-нибудь подобное
    // к тому же, лучше избавиться от логики здесь. Сюда лучше сразу проценты)
    fun position(newPosition: Int, count: Int) {
        val newState = (newPosition + 1) * 100 / count
        animationProgress(newState)
    }

    private fun animationProgress(current: Int) {
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