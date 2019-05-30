package com.example.bookingagent.di.component

import com.example.bookingagent.App
import com.example.bookingagent.di.modules.AppModule
import com.example.bookingagent.di.modules.BindingActivityModule
import com.example.bookingagent.di.modules.BindingFragmentModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AppModule::class,
		AndroidSupportInjectionModule::class,
		BindingActivityModule::class,
		BindingFragmentModule::class
	]
)
interface AppComponent : AndroidInjector<App> {

	@Component.Factory
	abstract class Factory : AndroidInjector.Factory<App>

}