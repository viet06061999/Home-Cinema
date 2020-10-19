package com.sun.homecinema.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sun.homecinema.data.model.Actor
import com.sun.homecinema.data.model.Movie
import com.sun.homecinema.data.source.local.database.dao.ActorDao
import com.sun.homecinema.data.source.local.database.dao.MovieDao

@Database(
    entities = [Movie::class, Actor::class],
    version = DatabaseConfig.DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun actorDao(): ActorDao
}
