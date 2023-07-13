package com.alexleru.showpictrure.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alexleru.showpictrure.presentation.view.mapper.CatPictureViewMapper
import com.alexleru.showpictrure.presentation.view.modelView.arch.PicturesEvent
import com.alexleru.showpictrure.presentation.view.modelView.arch.PicturesViewState
import com.alexleru.showpictrure.presentation.view.util.Event
import com.alexleru.showpicture.domain.usecases.GetListOfCatPictureUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class ListOfPicturesViewModel(private val getListOfCatPictureUseCase: GetListOfCatPictureUseCase) :
    ViewModel() {

    private val disposables = CompositeDisposable()
    private val catPictureViewMapper = CatPictureViewMapper()

    private val _viewStateSubject = BehaviorSubject.createDefault(PicturesViewState())
    val viewStateSubject: Observable<PicturesViewState>
        get() = _viewStateSubject

    private val _viewEventSubject = BehaviorSubject.create<Event<PicturesEvent>>()
    val viewEventSubject: Observable<Event<PicturesEvent>>
        get() = _viewEventSubject

//    private var _listOfCatPicture = MutableLiveData<List<CatPictureView>>()
//    val listOfCatPicture: LiveData<List<CatPictureView>>
//        get() = _listOfCatPicture

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
            .doOnSubscribe { updateState { copy(isRefreshing = true) } }
            .doOnTerminate { updateState { copy(isRefreshing = false) } }
            .subscribe({ list ->
                updateState { copy(catPictureView = list) }
            }, { error ->
                //TODO change on error screen
                Log.e("ERROR", "ERROR $error")
            }).also(disposables::add)
    }

    private fun updateState(updater: PicturesViewState.() -> PicturesViewState) {
        _viewStateSubject.value?.let { oldState ->
            _viewStateSubject.onNext(
                updater(oldState)
            )
        }
    }

    private fun handleOfError(throwable: Throwable) {
        updateState {
            if (catPictureView != null) {
               // _viewEventSubject.onNext(Event(PicturesEvent.ShowToast("Loading error. Please, refresh page")))
            copy()
            } else {
                copy(globalError = throwable)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}