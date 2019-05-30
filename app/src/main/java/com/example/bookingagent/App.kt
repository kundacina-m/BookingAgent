package com.example.bookingagent

import com.example.bookingagent.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
		DaggerAppComponent.factory().create(this)

}