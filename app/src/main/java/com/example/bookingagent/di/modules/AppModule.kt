package com.example.bookingagent.di.modules

import android.content.Context
import com.example.bookingagent.App
import com.example.bookingagent.data.db.AppDatabase
import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.networking.accommodation.AccommodationApi
import com.example.bookingagent.data.networking.helloworld.HelloWorldApi
import com.example.bookingagent.data.networking.user.UserApi
import com.example.bookingagent.data.repository.AccommodationRepository
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
	fun provideHelloWorldApi(): HelloWorldApi =
		Retrofit.Builder()
			.addConverterFactory(SimpleXmlConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.baseUrl(BASE_URL)
			.build()
			.create(HelloWorldApi::class.java)

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
	fun provideDb(appContext: Context): AppDatabase =
		AppDatabase.getInstance(appContext)

	@Singleton
	@Provides
	fun provideUserDao(db: AppDatabase): UserDao =
		db.userDao()

	@Singleton
	@Provides
	fun providesUserRepository(userDao: UserDao, userApi: UserApi) =
		UserRepository(userDao, userApi)

	@Singleton
	@Provides
	fun providesAccommodationRepository(accommodationApi: AccommodationApi) =
		AccommodationRepository(accommodationApi)

}