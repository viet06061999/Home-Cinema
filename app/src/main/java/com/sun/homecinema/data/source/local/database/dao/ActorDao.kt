package com.sun.homecinema.data.source.local.database.dao

import androidx.room.*
import com.sun.homecinema.data.model.Actor

@Dao
interface ActorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertActors(actors: List<Actor>)
}
