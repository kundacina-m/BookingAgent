package com.example.bookingagent.data.db.entities

data class Address(
    val latitude: Int,
    val longitude: Int,
    val city: String,
    val zipCode: Int,
    val street: String,
    val num: Int
)