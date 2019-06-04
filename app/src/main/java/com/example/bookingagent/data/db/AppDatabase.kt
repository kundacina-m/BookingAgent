package com.example.bookingagent.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.db.entities.LocalUserEntity

@Database(entities = [LocalUserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

	abstract fun userDao(): UserDao

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