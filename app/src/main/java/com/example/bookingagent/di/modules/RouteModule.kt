package com.example.bookingagent.di.modules

import android.app.Activity
import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.IRoutesFactory
import com.example.bookingagent.di.routes.RoutesFactory
import com.example.bookingagent.di.routes.RouteKey
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.home.HomeRoutes
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.login.LoginRoutes
import com.example.bookingagent.screens.main.MainActivity
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.register.RegisterRoutes
import com.example.bookingagent.di.routes.NavigationController
import com.example.bookingagent.di.routes.NavigationControllerImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RouteModule {

	@Binds
	internal abstract fun bindActivity(mainActivity: MainActivity): Activity

	@Binds
	internal abstract fun bindNavController(navigationControllerImpl: NavigationControllerImpl): NavigationController

	@Binds
	internal abstract fun bindFactory(routesModelFactory: RoutesFactory): IRoutesFactory

	@Binds
	@IntoMap
	@RouteKey(LoginFragment::class)
	internal abstract fun bindLoginRoutes(loginRoutes: LoginRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(RegisterFragment::class)
	internal abstract fun bindRegisterRoutes(loginRoutes: RegisterRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(HomeFragment::class)
	internal abstract fun bindHomeRoutes(homeRoutes: HomeRoutes): Routes

}
