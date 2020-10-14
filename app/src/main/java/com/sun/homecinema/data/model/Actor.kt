package com.sun.homecinema.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "actor")
data class Actor(
    @SerializedName("cast_id")
    @PrimaryKey
    val castId: Int,
    var castMovieId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val avatar: String
) : GeneraEntity {

    override fun areItemsTheSame(newItem: GeneraEntity): Boolean =
        newItem is Actor && this.castId == newItem.castId

    override fun areContentsTheSame(newItem: GeneraEntity): Boolean =
        newItem is Actor && this == newItem
}
