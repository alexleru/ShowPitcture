package com.alexleru.showpitcture.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.alexleru.showpitcture.databinding.ItemTextLayoutBinding
import com.alexleru.showpitcture.domain.entity.TextTitle

class ViewHolderText(private val binding: ItemTextLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TextTitle){
            binding.textView.text = item.text
        }
}
