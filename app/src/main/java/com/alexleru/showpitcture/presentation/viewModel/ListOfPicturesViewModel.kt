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

    // TODO
    // 1) Сначала public поля (если это LiveData, то можно юзать private совмещать с public),
    // потом private поля, потом public функции, потом private функции
    // ---
    // 2) Давай сделаем 1 LivaData, назовем её viewState, сделаем модель ListOfPicturesViewState()
    // и занесем туда все значения из лайвдат

    private val catPictureViewMapper = CatPictureViewMapper()


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

    val disposables = CompositeDisposable()
    private val catService: CatService by lazy { CatServiceImpl() }
    private var _listOfCatPicture = MutableLiveData<List<CatPictureView>>()
    val listOfCatPicture: LiveData<List<CatPictureView>>
        get() = _listOfCatPicture

    init {
        getListOfCatPictures()
    }

    private fun getListOfCatPictures() {
        catService.getListOfCatPictures()
            .map(catPictureViewMapper::mapListCatPictureFromApiResponseToView)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                _listOfCatPicture.value = list
            }, { error ->
                //TODO change on error screen
                // Давай отображать экран ошибки с кнопкой повтора, если данных нет, если что-то было загружено до этого, то отобразим тост
                // Events (Deprecated way, but.. :)) ): https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
                // True way: https://developer.android.com/topic/architecture/ui-layer/events
                Log.e("ERROR", "ERROR $error")
            }).also(disposables::add)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}