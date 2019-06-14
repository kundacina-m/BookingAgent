package com.example.bookingagent

import com.example.bookingagent.di.component.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
		DaggerAppComponent.factory().create(this)

	override fun onCreate() {
		super.onCreate()
		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this)
		}
	}

}