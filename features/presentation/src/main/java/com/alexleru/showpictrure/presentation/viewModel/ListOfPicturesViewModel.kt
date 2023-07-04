package com.alexleru.showpictrure.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexleru.showpictrure.presentation.view.mapper.CatPictureViewMapper
import com.alexleru.showpicture.domain.usecases.GetListOfCatPictureUseCase
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListOfPicturesViewModel(private val getListOfCatPictureUseCase: GetListOfCatPictureUseCase) :
    ViewModel() {

    private val disposables = CompositeDisposable()

    // TODO: Mapper тоже под DI
    private val catPictureViewMapper = CatPictureViewMapper()
    //private val catRepository: CatRepository by lazy { CatRepositoryImpl() }
    //private val getListOfCatPictureUseCase by lazy { GetListOfCatPictureUseCaseImpl(catRepository) }

    private var _listOfCatPicture = MutableLiveData<List<CatPictureView>>()
    val listOfCatPicture: LiveData<List<CatPictureView>>
        get() = _listOfCatPicture

    private val _progressPosition = MutableLiveData<Int>()
    val progressPosition: LiveData<Int>
        get() = _progressPosition

    fun position(first: Int, newPosition: Int, count: Int) {
        val newState = if (first == 0) {
            0
        } else {
            (newPosition + 1) * 100 / count
        }
        _progressPosition.value = newState
    }

    init {
        getListOfCatPictures()
    }

    private fun getListOfCatPictures() {
        getListOfCatPictureUseCase.invoke()
            .map(catPictureViewMapper::mapListCatPictureFromApiResponseToView)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                _listOfCatPicture.value = list
            }, { error ->
                //TODO change on error screen
                Log.e("ERROR", "ERROR $error")
            }).also(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}