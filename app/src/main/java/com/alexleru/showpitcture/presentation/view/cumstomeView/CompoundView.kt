// TODO: cuMstomE ;)) переименовать на custom
package com.alexleru.showpitcture.presentation.view.cumstomeView

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.view.updateLayoutParams
import com.alexleru.showpitcture.R
import com.alexleru.showpitcture.databinding.ImageCompoundViewBinding

private const val MINIMAL_TEXT_WIDTH_PERCENT_OF_IMAGE_VIEW = 30

// TODO: Create attr and style in theme
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

    // TODO: Можно выпилить
    ///Принимает значения от 0 до 100. Увеличивает ширину и шрифт TextView.
    var sizeRelativeToWidthCompound = 0

    init {
        // TODO: Use binding.inflate()
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

            // TODO: Всё работает :)
            getColor(R.styleable.CompoundView_backgroundTextCompound, Color.BLACK).run(
                textViewCompound::setBackgroundColor
            )

            getColor(R.styleable.CompoundView_textColorCompound, Color.BLACK).run(
                textViewCompound::setTextColor
            )

            getDrawable(R.styleable.CompoundView_imageCompound).run {
                imageViewCompound::setImageDrawable
            }
        }
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
}
