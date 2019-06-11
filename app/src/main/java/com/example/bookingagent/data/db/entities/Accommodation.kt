package com.example.bookingagent.data.db.entities

data class Accommodation(
    val name: String,
    val description: String,
    val address: Address,
    val type: String,
    val rooms: List<AccommodationUnit>
)