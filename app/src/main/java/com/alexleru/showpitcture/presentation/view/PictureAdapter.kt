package com.alexleru.showpitcture.presentation.view

import android.graphics.drawable.Drawable
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.alexleru.showpitcture.domain.entity.Picture

class PictureAdapter : RecyclerView.Adapter<ViewHolderPicture>() {

    var list = listOf<Picture>()

    var clickOnPicture: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPicture {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            200
        )
        return ViewHolderPicture(imageView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolderPicture, position: Int) {
        val item = list[position]
        val png = holder.itemView.context.assets.open(item.url)
        val dw = Drawable.createFromStream(png, null)
        with(holder.itemView) {
            (this as ImageView).setImageDrawable(dw)
            setOnClickListener { clickOnPicture?.invoke(item.url) }
        }
    }
}