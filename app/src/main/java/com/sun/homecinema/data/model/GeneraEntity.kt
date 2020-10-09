package com.sun.homecinema.data.model

interface GeneraEntity {
    fun areItemsTheSame(newItem: GeneraEntity): Boolean
    fun areContentsTheSame(newItem: GeneraEntity): Boolean
}
