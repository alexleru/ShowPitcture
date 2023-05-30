package com.alexleru.showpitcture.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexleru.showpitcture.data.RepositoryImpl
import com.alexleru.showpitcture.domain.entity.Picture
import com.alexleru.showpitcture.domain.usecases.GetListOfPicturesUseCase

class ListOfPicturesViewModel : ViewModel() {

    private val repository = RepositoryImpl()
    private val getListOfPicturesUseCase = GetListOfPicturesUseCase(repository)

    // TODO: val, ссылка на лайвдату не должна меняться)
    private var _listOfPicture = MutableLiveData<List<Picture>>()
    val listOfPicture
        get() = _listOfPicture

    init {
        _listOfPicture.value = getListOfPicturesUseCase.invoke()
    }

}