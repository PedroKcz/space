package io.space.presentation.di

import io.space.presentation.SpaceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun injectSpacePresentationKoinModule() = loadKoinModule

private val loadKoinModule by lazy {
    loadKoinModules(listOf(viewModelModule))
}

private val viewModelModule = module {
    viewModel { SpaceViewModel(get()) }
}
