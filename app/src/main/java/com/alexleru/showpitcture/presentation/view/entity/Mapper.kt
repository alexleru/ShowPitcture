package com.alexleru.showpitcture.presentation.view.entity

import com.alexleru.showpitcture.domain.entity.ItemData
import com.alexleru.showpitcture.domain.entity.ItemData.Picture
import com.alexleru.showpitcture.domain.entity.ItemData.TextTitle
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel.PictureViewModel
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel.TextTitleViewModel

class Mapper {

    fun mapToItemDataViewModel(itemData: ItemData): ItemDataViewModel {
        return when (itemData) {
            is Picture -> PictureViewModel(
                uuid = itemData.uuid,
                url = itemData.url,
                date = itemData.date,
                favorite = itemData.favorite
            )


            is TextTitle -> TextTitleViewModel(
                uuid = itemData.uuid,
                text = itemData.text,
                position = itemData.position
            )
        }
    }

    fun mapToItemData(itemDataViewModel: ItemDataViewModel): ItemData {
        return when (itemDataViewModel) {
            is PictureViewModel -> Picture(
                uuid = itemDataViewModel.uuid,
                url = itemDataViewModel.url,
                date = itemDataViewModel.date,
                favorite = itemDataViewModel.favorite
            )

            is TextTitleViewModel -> TextTitle(
                uuid = itemDataViewModel.uuid,
                text = itemDataViewModel.text,
                position = itemDataViewModel.position
            )
        }
    }

    fun mapToItemDataViewModel(itemDataList: List<ItemData>): List<ItemDataViewModel> {
        return itemDataList.map { mapToItemDataViewModel(it) }
    }
}