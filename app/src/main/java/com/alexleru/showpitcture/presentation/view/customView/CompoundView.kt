package com.alexleru.showpitcture.presentation.view.customView

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.alexleru.showpitcture.R
import com.alexleru.showpitcture.databinding.ImageCompoundViewBinding


private const val MINIMAL_TEXT_WIDTH_PERCENT_OF_IMAGE_VIEW = 30
private val DEFAULT_STYLE_ATTR = R.attr.viewStyleCompoundViewDefault

class CompoundView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(
    context, attrs, defStyleAttr, defStyleRes
) {

    private val bind: ImageCompoundViewBinding by lazy { ImageCompoundViewBinding.bind(this) }

    var textDate
        get() = bind.textViewCompound.text.toString()
        set(value) {
            bind.textViewCompound.text = value
        }

    ///Принимает значения от 0 до 100. Увеличивает ширину и шрифт TextView.
    var sizeRelativeToWidthCompound = 0


    init {
        View.inflate(getContext(), R.layout.image_compound_view, this)

        getContext().withStyledAttributes(
            attrs,
            R.styleable.CompoundView,
            DEFAULT_STYLE_ATTR,
            defStyleRes
        ) {
            textDate = getString(R.styleable.CompoundView_textCompound).orEmpty()
            sizeRelativeToWidthCompound =
                getInt(
                    R.styleable.CompoundView_sizeRelativeToWidthCompound,
                    MINIMAL_TEXT_WIDTH_PERCENT_OF_IMAGE_VIEW
                )
            getColor(R.styleable.CompoundView_backgroundTextCompound, Color.BLACK).run {
                bind.textViewCompound.setBackgroundColor(this)
            }

            getColor(R.styleable.CompoundView_textColorCompound, Color.BLACK).run {
                bind.textViewCompound.setTextColor(this)
            }

            getDrawable(R.styleable.CompoundView_imageCompound).run {
                bind.imageViewCompound::setImageDrawable
            }
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        calculateSizeTextView()
    }

    private fun calculateSizeTextView() {
        Log.d("imageViewCompound+++", bind.imageViewCompound.width.toString())
        Log.d("textViewCompound_OLD_WIDTH+++", bind.textViewCompound.width.toString())
        Log.d("sizeRelativeToWidthCompound+++", sizeRelativeToWidthCompound.toString())
        val widthOfImageView = bind.imageViewCompound.width
        //TODO при использовании в recycleview не присваиваются какие либо значения.
        // В данном случае textViewCompound.width после присваивания, все равно оставляет прежнее значение
        // При этом при использовании в следующем фрагменте все норм.
        bind.textViewCompound.layoutParams.width =
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
        Log.d("imageViewCompoundF+++", bind.imageViewCompound.width.toString())
        Log.d("textViewCompound_NEW_WIDTH+++", bind.textViewCompound.width.toString())

        val size = getFitTextSize(
            bind.textViewCompound.paint,
            bind.textViewCompound.width.toFloat(),
            textDate
        )
        bind.textViewCompound.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
        Log.d("getFitTextSize++++", bind.textViewCompound.textSize.toString())
    }


    fun setImageCompound(image: Drawable) {
        bind.imageViewCompound.setImageDrawable(image)
    }

    fun getImageCompound() = bind.imageViewCompound

    fun setTextBackgroundColor(@ColorRes color: Int) {
        bind.textViewCompound.setBackgroundResource(color)
    }

    fun setTextColor(@ColorRes color: Int) {
        bind.textViewCompound.setTextColor(ContextCompat.getColor(context, color))
    }

    private fun getFitTextSize(paint: TextPaint, width: Float, text: String?): Float {
        val nowWidth = paint.measureText(text)
        return if (nowWidth !in (width - 30)..(width - 10)) {
            (width - 20) / nowWidth * paint.textSize
        } else
            paint.textSize
    }

}
