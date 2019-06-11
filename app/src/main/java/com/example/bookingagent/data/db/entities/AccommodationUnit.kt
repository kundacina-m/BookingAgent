package com.example.bookingagent.data.db.entities

data class AccommodationUnit(
    val num: Int,
    val floor: Int,
    val bedNum: Int,
    val price: Int,
    val images: List<String>
)
