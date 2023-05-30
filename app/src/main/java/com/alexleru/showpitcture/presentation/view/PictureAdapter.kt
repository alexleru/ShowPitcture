package com.alexleru.showpitcture.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alexleru.showpitcture.domain.entity.Picture
import com.alexleru.showpitcture.fromAssertToDrawable

class PictureAdapter : RecyclerView.Adapter<ViewHolderPicture>() {

    // TODO: emptyList()
    var list = listOf<Picture>()
//    var list = emptyList<Picture>()

    var clickOnPicture: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPicture {
        //TODO: обсудить хорошая ли идея тут создавать view? Довольно сложно в коде стили применять
        // RE: LayoutInflater.from ;)
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            400
        )
        return ViewHolderPicture(imageView)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolderPicture, position: Int) {
        // TODO: наполнение вьюхи нужно делать внутри ViewHolder, иначе вьюхолдер не нужен)
        val item = list[position]
        // TODO: Лучше не сокрашать имена переменных, когда такого много, код сложно читать)
        val dw = fromAssertToDrawable(holder.itemView.context, item.url)
        with(holder.itemView) {
            // TODO: Потенциальный краш, лучше кастить через (this as? ImageView)?.setImageDrawable(dw), так безопаснее
            (this as? ImageView)?.setImageDrawable(dw)
            setOnClickListener { clickOnPicture?.invoke(item.url) }
        }
    }
}