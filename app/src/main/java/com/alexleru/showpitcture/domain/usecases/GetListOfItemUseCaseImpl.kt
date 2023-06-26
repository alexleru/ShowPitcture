package com.alexleru.showpitcture.domain.usecases

import androidx.lifecycle.LiveData
import com.alexleru.showpitcture.domain.Repository
import com.alexleru.showpitcture.domain.entity.ItemData

class GetListOfItemUseCaseImpl(private val repository: Repository): GetListOfItemUseCase {
    override operator fun invoke(): LiveData<List<ItemData>> {
        return repository.getListOfItem()
    }
}