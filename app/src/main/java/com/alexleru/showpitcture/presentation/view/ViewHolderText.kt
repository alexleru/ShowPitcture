package com.alexleru.showpitcture.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.alexleru.showpitcture.databinding.ItemTextLayoutBinding
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel.TextTitleViewModel

class ViewHolderText(private val binding: ItemTextLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TextTitleViewModel) {
        binding.textView.text = item.text
    }
}
