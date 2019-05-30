package com.example.bookingagent.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookingagent.di.ViewModelFactory
import com.example.bookingagent.di.ViewModelKey
import com.example.bookingagent.screens.login.LoginViewModel
import com.example.bookingagent.screens.register.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

	@Binds
	internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

	@Binds
	@IntoMap
	@ViewModelKey(LoginViewModel::class)
	internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

	@Binds
	@IntoMap
	@ViewModelKey(RegisterViewModel::class)
	internal abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

}