package com.sun.homecinema.data.source

import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.MovieActorResponse
import com.sun.homecinema.data.model.MovieListResponse
import io.reactivex.Observable

interface ActorDataSource {

    interface Remote{

        fun getMovieOfActor(actorId: Int): Observable<MovieActorResponse>

        fun getDetailActor(actorId: Int): Observable<Actor>
    }
}
