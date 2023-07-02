package com.alexleru.showpitcture.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexleru.showpitcture.data.network.service.CatService
import com.alexleru.showpitcture.data.network.service.CatServiceImpl
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureView
import com.alexleru.showpitcture.presentation.view.modelView.CatPictureViewMapper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ListOfPicturesViewModel : ViewModel() {

    private val disposables = CompositeDisposable()
    private val catPictureViewMapper = CatPictureViewMapper()
    private val catService: CatService by lazy { CatServiceImpl() }

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
        catService.getListOfCatPictures()
            .map ( catPictureViewMapper::mapListCatPictureFromApiResponseToView )
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