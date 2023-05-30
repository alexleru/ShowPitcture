package com.alexleru.showpitcture

import android.content.Context
import android.graphics.drawable.Drawable

fun fromAssertToDrawable(context: Context, url: String): Drawable {
    val png = context.assets.open(url)
    return Drawable.createFromStream(png, null)
        ?: throw RuntimeException("fromAssertToDrawable() == null")
}