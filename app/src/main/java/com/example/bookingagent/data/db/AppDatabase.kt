package com.example.bookingagent.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookingagent.data.db.dao.AccRoomDao
import com.example.bookingagent.data.db.dao.AccommodationDao
import com.example.bookingagent.data.db.dao.RoomDao
import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.db.entities.AccRoom
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.User
import com.example.bookingagent.data.db.utils.Converters

@TypeConverters(Converters::class)
@Database(entities = [User::class, Accommodation::class, AccRoom::class, com.example
	.bookingagent.data.db.entities.Room::class], version
= 1)
abstract class AppDatabase : RoomDatabase() {

	abstract fun userDao(): UserDao
	abstract fun accommodationDao(): AccommodationDao
	abstract fun accRoomDao(): AccRoomDao
	abstract fun roomDao(): RoomDao

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