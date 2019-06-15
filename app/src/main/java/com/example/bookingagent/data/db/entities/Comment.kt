package com.example.bookingagent.data.db.entities

import androidx.room.PrimaryKey

data class Comment(
    @PrimaryKey
    val id: Long,
    val username: String,
    val content: String
)