package com.alexleru.showpitcture.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.alexleru.showpitcture.data.RepositoryImpl
import com.alexleru.showpitcture.domain.entity.Picture
import com.alexleru.showpitcture.domain.usecases.GetListOfItemUseCaseImpl
import com.alexleru.showpitcture.domain.usecases.SetFavoriteOfPictureImpl

class ListOfPicturesViewModel: ViewModel() {

    private val repository = RepositoryImpl()
    private val getListOfPicturesUseCase = GetListOfItemUseCaseImpl(repository)
    private val setFavoriteOfPicturesUseCase = SetFavoriteOfPictureImpl(repository)

    val listOfPicture = getListOfPicturesUseCase.invoke()

    fun setFavoriteOfPicture(oldValue: Picture){
        setFavoriteOfPicturesUseCase.invoke(oldValue)
    }

}