package com.alexleru.showpicture.domain.di

import com.alexleru.showpicture.domain.usecases.GetListOfCatPictureUseCase
import com.alexleru.showpicture.domain.usecases.GetListOfCatPictureUseCaseImpl
import org.koin.dsl.module

val catDomainModule = module {
    single<GetListOfCatPictureUseCase> {
        GetListOfCatPictureUseCaseImpl(repository = get())
    }
}