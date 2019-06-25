package com.example.bookingagent.di.modules

import android.content.Context
import com.example.bookingagent.App
import com.example.bookingagent.data.db.AppDatabase
import com.example.bookingagent.data.db.dao.AccRoomDao
import com.example.bookingagent.data.db.dao.AccommodationDao
import com.example.bookingagent.data.db.dao.ReservationDao
import com.example.bookingagent.data.db.dao.RoomDao
import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.networking.accommodation.AccommodationApi
import com.example.bookingagent.data.networking.reservation.ReservationApi
import com.example.bookingagent.data.networking.room.RoomApi
import com.example.bookingagent.data.networking.user.UserApi
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.data.repository.ReservationRepository
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.di.viewmodel.ViewModelModule
import com.example.bookingagent.utils.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

	@Provides
	fun provideContext(application: App): Context {
		return application.applicationContext
	}

	@Singleton
	@Provides
	fun provideUserApi(): UserApi =
		Retrofit.Builder()
			.addConverterFactory(SimpleXmlConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.baseUrl(BASE_URL)
			.build()
			.create(UserApi::class.java)

	@Singleton
	@Provides
	fun provideAccommodationApi(): AccommodationApi =
		Retrofit.Builder()
			.addConverterFactory(SimpleXmlConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.baseUrl(BASE_URL)
			.build()
			.create(AccommodationApi::class.java)

	@Singleton
	@Provides
	fun provideRoomApi(): RoomApi =
		Retrofit.Builder()
			.addConverterFactory(SimpleXmlConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.baseUrl(BASE_URL)
			.build()
			.create(RoomApi::class.java)

	@Singleton
	@Provides
	fun provideReservationApi(): ReservationApi =
		Retrofit.Builder()
			.addConverterFactory(SimpleXmlConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.baseUrl(BASE_URL)
			.build()
			.create(ReservationApi::class.java)

	@Singleton
	@Provides
	fun provideDb(appContext: Context): AppDatabase =
		AppDatabase.getInstance(appContext)

	@Singleton
	@Provides
	fun provideUserDao(db: AppDatabase): UserDao =
		db.userDao()

	@Singleton
	@Provides
	fun provideAccRoomDao(db: AppDatabase): AccRoomDao =
		db.accRoomDao()

	@Singleton
	@Provides
	fun provideAccommodationDao(db: AppDatabase): AccommodationDao =
		db.accommodationDao()

	@Singleton
	@Provides
	fun provideRoomDao(db: AppDatabase): RoomDao =
		db.roomDao()

	@Provides
	fun provideReservationDao(db: AppDatabase): ReservationDao =
		db.reservationDao()

	@Singleton
	@Provides
	fun providesUserRepository(userDao: UserDao, userApi: UserApi) =
		UserRepository(userDao, userApi)

	@Singleton
	@Provides
	fun providesAccommodationRepository(accommodationDao: AccommodationDao, accommodationApi: AccommodationApi) =
		AccommodationRepository(accommodationDao, accommodationApi)

	@Singleton
	@Provides
	fun providesRoomRepository(roomApi: RoomApi, roomDao: RoomDao, accRoomDao: AccRoomDao) =
		RoomRepository(roomApi, roomDao, accRoomDao)

	@Singleton
	@Provides
	fun providesReservationRepository(reservationApi: ReservationApi, reservationDao: ReservationDao) =
		ReservationRepository(reservationApi, reservationDao)
}