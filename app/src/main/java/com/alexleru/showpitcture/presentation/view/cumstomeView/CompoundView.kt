package com.alexleru.showpitcture.presentation.view.cumstomeView

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.alexleru.showpitcture.R


private const val MINIMAL_TEXT_WIDTH_PERCENT_OF_IMAGE_VIEW = 30

class CompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(
    context, attrs, defStyleAttr, defStyleRes
) {

    private val imageViewCompound by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<ImageView>(R.id.imageViewCompound)
    }

    private val textViewCompound by lazy(LazyThreadSafetyMode.NONE) {
        findViewById<TextView>(R.id.textViewCompound)
    }

    var textDate
        get() = textViewCompound.text.toString()
        set(value) {
            textViewCompound.text = value
        }

    ///Принимает значения от 0 до 100. Увеличивает ширину и шрифт TextView.
    var sizeRelativeToWidthCompound = 0


    init {
        View.inflate(getContext(), R.layout.image_compound_view, this)

        getContext().withStyledAttributes(
            attrs,
            R.styleable.CompoundView,
            defStyleAttr,
            defStyleRes
        ) {
            textDate = getString(R.styleable.CompoundView_textCompound).orEmpty()
            sizeRelativeToWidthCompound =
                getInt(
                    R.styleable.CompoundView_sizeRelativeToWidthCompound,
                    MINIMAL_TEXT_WIDTH_PERCENT_OF_IMAGE_VIEW
                )
            getColor(R.styleable.CompoundView_backgroundTextCompound, Color.BLACK).run {
                textViewCompound.setBackgroundColor(this)
                //Todo: странно, но вот так не заработало textViewCompound::setBackgroundColor
            }

            getColor(R.styleable.CompoundView_textColorCompound, Color.BLACK).run {
                textViewCompound.setTextColor(this)
            }

            getDrawable(R.styleable.CompoundView_imageCompound).run {
                imageViewCompound::setImageDrawable
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        calculateSizeTextView()
    }

    private fun calculateSizeTextView() {
        Log.d("imageViewCompound+++", imageViewCompound.width.toString())
        Log.d("textViewCompound_OLD_WIDTH+++", textViewCompound.width.toString())
        Log.d("sizeRelativeToWidthCompound+++", sizeRelativeToWidthCompound.toString())
        val widthOfImageView = imageViewCompound.width
        //TODO при использовании в recycleview не присваиваются какие либо значения.
        // В данном случае textViewCompound.width после присваивания, все равно оставляет прежнее значение
        // При этом при использовании в следующем фрагменте все норм.
        textViewCompound.width =
            if (sizeRelativeToWidthCompound < MINIMAL_TEXT_WIDTH_PERCENT_OF_IMAGE_VIEW
                || sizeRelativeToWidthCompound > 100
            ) {
                widthOfImageView * MINIMAL_TEXT_WIDTH_PERCENT_OF_IMAGE_VIEW / 100
            } else {
                Log.d(
                    "->>+++",
                    (widthOfImageView * sizeRelativeToWidthCompound / 100).toString()
                )
                widthOfImageView * sizeRelativeToWidthCompound / 100
            }
        Log.d("imageViewCompoundF+++", imageViewCompound.width.toString())
        Log.d("textViewCompound_NEW_WIDTH+++", textViewCompound.width.toString())

        val size =
            getFitTextSize(textViewCompound.paint, textViewCompound.width.toFloat(), textDate)
        textViewCompound.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
        Log.d("getFitTextSize++++", textViewCompound.textSize.toString())
    }


    fun setImageCompound(image: Drawable) {
        imageViewCompound.setImageDrawable(image)
    }

    fun setTextBackgroundColor(@ColorRes color: Int) {
        textViewCompound.setBackgroundResource(color)
    }

    fun setTextColor(@ColorRes color: Int) {
        textViewCompound.setTextColor(ContextCompat.getColor(context, color))
    }

    private fun getFitTextSize(paint: TextPaint, width: Float, text: String?): Float {
        val nowWidth = paint.measureText(text)
        return if (nowWidth !in (width - 30)..(width - 10)) {
            (width - 20) / nowWidth * paint.textSize
        } else
            paint.textSize
    }

}
