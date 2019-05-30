package com.example.bookingagent.di.modules

import com.example.bookingagent.di.scopes.FragmentScope
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.home.HomeViewModel
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.login.LoginViewModel
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.register.RegisterViewModel
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BindingFragmentModule {

	@FragmentScope
	@ContributesAndroidInjector(modules = [LoginViewModel::class])
	abstract fun contributeLoginFragmentInjector(): LoginFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [RegisterViewModel::class])
	abstract fun contributeRegisterFragmentInjector(): RegisterFragment

	@FragmentScope
	@ContributesAndroidInjector(modules = [HomeViewModel::class])
	abstract fun contributeHomeFragmentInjector(): HomeFragment

}