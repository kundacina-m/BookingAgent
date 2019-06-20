package com.example.bookingagent.domain

import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.domain.crud.LocalCRUD

interface AccommodationLocalStorage : LocalCRUD<Accommodation> {
}