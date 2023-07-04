package com.alexleru.showpictrure.presentation.view.di

import com.alexleru.showpictrure.presentation.viewModel.ListOfPicturesViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val catsPresentationModule = module {
    viewModel {
        ListOfPicturesViewModel(
            getListOfCatPictureUseCase = get()
        )
    }

    factory {
        CompositeDisposable()
    }
}