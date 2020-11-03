package com.sun.homecinema.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class MovieWithActors(
    @Embedded val movie: Movie,
    @Relation(
        parentColumn = COLUMN_PARENT,
        entityColumn = COLUMN_ENTITY
    )
    val actors: List<Actor>
) : GeneraEntity {

    override fun areItemsTheSame(newItem: GeneraEntity): Boolean =
        newItem is MovieWithActors && this.movie.movieId == newItem.movie.movieId

    override fun areContentsTheSame(newItem: GeneraEntity): Boolean =
        newItem is MovieWithActors && this == newItem

    companion object{
        const val COLUMN_PARENT = "movieId"
        const val COLUMN_ENTITY = "castMovieId"
    }
}
