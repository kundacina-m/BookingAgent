package com.example.bookingagent.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookingagent.data.db.dao.*
import com.example.bookingagent.data.db.entities.*
import com.example.bookingagent.data.db.utils.Converters

@TypeConverters(Converters::class)
@Database(
    entities = [UserEntity::class, AccommodationEntity::class, AccRoom::class, RoomEntity::class,
        ReservationEntity::class, MessageEntity::class, ResMessEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun accommodationDao(): AccommodationDao
    abstract fun accRoomDao(): AccRoomDao
    abstract fun roomDao(): RoomDao
    abstract fun reservationDao(): ReservationDao
    abstract fun messageDao(): MessageDao
    abstract fun resMessDao(): ResMessDao


    companion object {
        private var INSTANCE: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "app-db").build()

    }

}