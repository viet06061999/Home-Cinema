package com.sun.homecinema.di

import androidx.room.Room
import com.sun.homecinema.data.repository.*
import com.sun.homecinema.data.source.ActorDataSource
import com.sun.homecinema.data.source.FavoriteDataSource
import com.sun.homecinema.data.source.MovieDataSource
import com.sun.homecinema.data.source.local.FavoriteLocalDataSource
import com.sun.homecinema.data.source.local.database.AppDatabase
import com.sun.homecinema.data.source.local.database.DatabaseConfig.DATABASE_NAME
import com.sun.homecinema.data.source.remote.ActorRemoteDataSource
import com.sun.homecinema.data.source.remote.MovieRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single { get<AppDatabase>().actorDao() }
    single { get<AppDatabase>().movieDao()}
}

val favoriteRepoModule = module {
    single<FavoriteDataSource.Local> { FavoriteLocalDataSource(get(), get()) }

    single<FavoriteRepository> { FavoriteRepositoryImpl(get()) }
}

val movieRepoModule = module {
    single<MovieDataSource.Remote> { MovieRemoteDataSource(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }
}

val actorRepoModule = module {
    single<ActorDataSource.Remote> { ActorRemoteDataSource(get()) }

    single<ActorRepository> { ActorRepositoryImpl(get()) }
}
