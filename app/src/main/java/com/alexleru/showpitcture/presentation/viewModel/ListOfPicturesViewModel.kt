package com.alexleru.showpitcture.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexleru.showpitcture.data.RepositoryImpl
import com.alexleru.showpitcture.domain.entity.Picture
import com.alexleru.showpitcture.domain.usecases.GetListOfItemUseCaseImpl
import com.alexleru.showpitcture.domain.usecases.SetFavoriteOfPictureUseCaseImpl

class ListOfPicturesViewModel: ViewModel() {

    private val repository = RepositoryImpl()
    private val getListOfPicturesUseCase = GetListOfItemUseCaseImpl(repository)
    private val setFavoriteOfPicturesUseCase = SetFavoriteOfPictureUseCaseImpl(repository)

    val listOfPicture = getListOfPicturesUseCase.invoke()

    fun setFavoriteOfPicture(picture: Picture){
        setFavoriteOfPicturesUseCase.invoke(picture)
    }

    private val _progressPosition = MutableLiveData<Int>()
    val progressPosition: LiveData<Int>
        get() = _progressPosition

    fun position(newPosition: Int, count: Int) {
        val newState = (newPosition + 1) * 100 / count
        _progressPosition.value = newState
    }

}