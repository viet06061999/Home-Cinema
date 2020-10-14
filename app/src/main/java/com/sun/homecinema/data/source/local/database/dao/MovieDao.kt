package com.sun.homecinema.data.source.local.database.dao

import androidx.room.*
import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.model.MovieWithActors
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insertMovie(movie: Movie)

    @Query("SELECT * FROM Movie")
     fun getMovieWithActors(): Flowable<List<MovieWithActors>>

    @Delete
     fun delete(movie: Movie, actors: List<Actor>): Completable
}
