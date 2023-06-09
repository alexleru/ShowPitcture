package com.alexleru.showpitcture.domain.usecases

import androidx.lifecycle.LiveData
import com.alexleru.showpitcture.domain.entity.ItemData
import com.alexleru.showpitcture.domain.entity.Picture

interface GetListOfItemUseCase {
    operator fun invoke(): LiveData<List<ItemData>>
}