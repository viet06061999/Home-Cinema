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
) {
    companion object{
        const val COLUMN_PARENT = "movieId"
        const val COLUMN_ENTITY = "castMovieId"
    }
}
