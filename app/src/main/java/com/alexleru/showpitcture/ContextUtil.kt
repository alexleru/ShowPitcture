package com.alexleru.showpitcture

import android.content.Context
import android.graphics.drawable.Drawable

fun Context.fromAssertToDrawable(url: String): Drawable {
    val png = this.assets.open(url)
    return Drawable.createFromStream(png, null)
        ?: throw RuntimeException("fromAssertToDrawable() == null")
}