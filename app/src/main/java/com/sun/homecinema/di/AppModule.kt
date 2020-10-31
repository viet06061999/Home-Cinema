package com.sun.homecinema.di

import com.sun.homecinema.ui.detail.DetailViewModel
import com.sun.homecinema.ui.detailactor.DetailActorViewModel
import com.sun.homecinema.ui.favorite.FavoriteViewModel
import com.sun.homecinema.ui.home.HomeViewModel
import com.sun.homecinema.ui.movies.GenreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { GenreViewModel(get())}
    viewModel { DetailActorViewModel(get())}
}
