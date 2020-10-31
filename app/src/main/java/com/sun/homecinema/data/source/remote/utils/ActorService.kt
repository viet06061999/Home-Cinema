package com.sun.homecinema.data.source.remote.utils

import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.ActorResponse
import com.sun.homecinema.data.model.MovieActorResponse
import com.sun.homecinema.data.model.MovieListResponse
import com.sun.homecinema.utils.ApiEndPoint
import com.sun.homecinema.utils.ApiEndPoint.PARAMS_ID
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ActorService {
    @GET(ApiEndPoint.GET_MOVIE_OF_ACTOR)
    fun getMovieOfActor(@Path(PARAMS_ID) actorId: Int): Observable<MovieActorResponse>

    @GET(ApiEndPoint.GET_DETAIL_ACTOR)
    fun getDetailActor(@Path(PARAMS_ID) actorId: Int): Observable<Actor>
}
