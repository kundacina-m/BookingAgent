package com.example.bookingagent.di.modules

import android.app.Activity
import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.IRoutesFactory
import com.example.bookingagent.di.routes.NavigationController
import com.example.bookingagent.di.routes.NavigationControllerImpl
import com.example.bookingagent.di.routes.RouteKey
import com.example.bookingagent.di.routes.RoutesFactory
import com.example.bookingagent.screens.accommodations.AccommodationsFragment
import com.example.bookingagent.screens.addaccommodation.AddAccommodationFragment
import com.example.bookingagent.screens.addaccommodation.AddAccommodationRoutes
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.home.HomeRoutes
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.login.LoginRoutes
import com.example.bookingagent.screens.main.MainActivity
import com.example.bookingagent.screens.messages.MessagesFragment
import com.example.bookingagent.screens.messages.MessagesRoutes
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.register.RegisterRoutes
import com.example.bookingagent.screens.reservations.ReservationRoutes
import com.example.bookingagent.screens.reservations.ReservationsFragment
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

	@Binds
	@IntoMap
	@RouteKey(AddAccommodationFragment::class)
	internal abstract fun bindAddAccommodationRoutes(addAccommodationRoutes: AddAccommodationRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(AccommodationsFragment::class)
	internal abstract fun bindAccommodationsRoutes(accommodationRoutes: AddAccommodationRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(ReservationsFragment::class)
	internal abstract fun bindReservationsRoutes(reservationRoutes: ReservationRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(MessagesFragment::class)
	internal abstract fun bindMessagesRoutes(messagesRoutes: MessagesRoutes): Routes

}
