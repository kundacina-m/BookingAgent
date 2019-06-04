package com.example.bookingagent.di.modules

import android.content.Context
import com.example.bookingagent.App
import com.example.bookingagent.data.db.AppDatabase
import com.example.bookingagent.data.db.dao.UserDao
import com.example.bookingagent.data.networking.TestApi
import com.example.bookingagent.data.repository.UserRepository
import com.example.bookingagent.di.viewmodel.ViewModelModule
import dagger.Module
import dagger.Provides
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
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
	fun provideArticleApi(): TestApi =
		Retrofit.Builder()
			.addConverterFactory(SimpleXmlConverterFactory.create())
			.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
			.baseUrl("https://gcomputer.net")
			.build()
			.create(TestApi::class.java)

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
	fun providesUserRepository(userDao: UserDao) =
		UserRepository(userDao)

}