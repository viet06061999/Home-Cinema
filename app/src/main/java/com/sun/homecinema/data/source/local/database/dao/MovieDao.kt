package com.sun.homecinema.data.source.local.database.dao

import androidx.room.*
import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieWithActors
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertMovie(movie: Movie)

    @Query("SELECT * FROM movie")
     fun getMovieWithActors(): Flowable<List<MovieWithActors>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE movieId = :movieId) ")
    fun isFavorite(movieId: Int): Single<Boolean>

    @Delete
     fun delete(movie: Movie, actors: List<Actor>): Completable
}
