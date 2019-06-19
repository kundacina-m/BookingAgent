package com.example.bookingagent.domain

import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.domain.crud.LocalCRUD

interface RoomLocalStorage : LocalCRUD<Room> {
}