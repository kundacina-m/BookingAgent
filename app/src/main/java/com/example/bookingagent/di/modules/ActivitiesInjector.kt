package com.example.bookingagent.di.modules

import com.example.bookingagent.di.scopes.ActivityScope
import com.example.bookingagent.screens.main.MainActivity
import com.example.bookingagent.screens.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesInjector {

	@ActivityScope
	@ContributesAndroidInjector(modules = [MainActivityModule::class])
	abstract fun contributeMainActivityInjector(): MainActivity

}