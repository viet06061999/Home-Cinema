package com.sun.homecinema.di

import com.sun.homecinema.data.source.remote.utils.ActorService
import com.sun.homecinema.data.source.remote.utils.MovieService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(ActorService::class.java) }
    single {  get<Retrofit>().create(MovieService::class.java)}
}
