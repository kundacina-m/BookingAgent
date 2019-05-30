package com.example.bookingagent.di.modules

import android.content.Context
import com.example.bookingagent.App
import com.example.bookingagent.di.modules.ViewModelModule
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class])
class AppModule {

	@Provides
	fun provideContext(application: App): Context {
		return application.applicationContext
	}
}