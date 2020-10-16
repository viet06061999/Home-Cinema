package com.sun.homecinema.data.source.remote

import com.sun.homecinema.data.model.ActorResponse
import com.sun.homecinema.data.model.MovieListResponse
import com.sun.homecinema.data.source.ActorDataSource
import com.sun.homecinema.data.source.remote.utils.ActorService
import io.reactivex.Observable

class ActorRemoteDataSource(private val actorService: ActorService) :
    ActorDataSource.Remote {

    override fun getMovieOfActor(actorId: Int): Observable<MovieListResponse> =
        actorService.getMovieOfActor(actorId)

    override fun getDetailActor(actorId: Int): Observable<ActorResponse> =
        actorService.getDetailActor(actorId)
}
