package com.alexleru.showpitcture.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexleru.showpitcture.data.RepositoryImpl
import com.alexleru.showpitcture.domain.entity.ItemData.Picture
import com.alexleru.showpitcture.domain.usecases.GetListOfItemUseCaseImpl
import com.alexleru.showpitcture.domain.usecases.SetFavoriteOfPictureUseCaseImpl
import com.alexleru.showpitcture.presentation.view.entity.ItemDataViewModel
import com.alexleru.showpitcture.presentation.view.entity.Mapper

class ListOfPicturesViewModel : ViewModel() {

    private val repository = RepositoryImpl()
    private val getListOfItemsUseCase = GetListOfItemUseCaseImpl(repository)
    private val setFavoriteOfPicturesUseCase = SetFavoriteOfPictureUseCaseImpl(repository)
    private val mapper = Mapper()

//    val listOfItems = getListOfItemsUseCase.invoke()

    fun getListOfItems(): LiveData<List<ItemDataViewModel>> {
        return MediatorLiveData<List<ItemDataViewModel>>().apply {
            addSource(getListOfItemsUseCase.invoke()) {
                value = mapper.mapToItemDataViewModel(it)
            }
        }
    }

//    fun setFavoriteOfPicture(picture: Picture) {
//        setFavoriteOfPicturesUseCase.invoke(picture)
//    }

    fun setFavoriteOfPicture(picturesViewModel: ItemDataViewModel.PictureViewModel) {
        setFavoriteOfPicturesUseCase.invoke(mapper.mapToItemData(picturesViewModel) as Picture)
    }

    private val _progressPosition = MutableLiveData<Int>()
    val progressPosition: LiveData<Int>
        get() = _progressPosition

    fun position(newPosition: Int, count: Int) {
        val newState = (newPosition + 1) * 100 / count
        _progressPosition.value = newState
    }

}