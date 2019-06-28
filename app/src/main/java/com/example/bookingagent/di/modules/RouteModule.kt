package com.example.bookingagent.di.modules

import android.app.Activity
import com.example.bookingagent.Routes
import com.example.bookingagent.di.routes.IRoutesFactory
import com.example.bookingagent.di.routes.NavigationController
import com.example.bookingagent.di.routes.NavigationControllerImpl
import com.example.bookingagent.di.routes.RouteKey
import com.example.bookingagent.di.routes.RoutesFactory
import com.example.bookingagent.screens.accommodations.add.AddAccommodationFragment
import com.example.bookingagent.screens.accommodations.add.AddAccommodationRoutes
import com.example.bookingagent.screens.accommodations.details.AccommodationDetailsFragment
import com.example.bookingagent.screens.accommodations.details.AccommodationDetailsRoutes
import com.example.bookingagent.screens.accommodations.edit.EditAccommodationFragment
import com.example.bookingagent.screens.accommodations.edit.EditAccommodationRoutes
import com.example.bookingagent.screens.accommodations.list.AccommodationsFragment
import com.example.bookingagent.screens.accommodations.list.AccommodationsRoutes
import com.example.bookingagent.screens.home.HomeFragment
import com.example.bookingagent.screens.home.HomeRoutes
import com.example.bookingagent.screens.login.LoginFragment
import com.example.bookingagent.screens.login.LoginRoutes
import com.example.bookingagent.screens.main.MainActivity
import com.example.bookingagent.screens.messages.MessagesFragment
import com.example.bookingagent.screens.messages.MessagesRoutes
import com.example.bookingagent.screens.messages.thread.MessageThreadFragment
import com.example.bookingagent.screens.messages.thread.MessageThreadRoutes
import com.example.bookingagent.screens.profile.ProfileFragment
import com.example.bookingagent.screens.profile.ProfileRoutes
import com.example.bookingagent.screens.register.RegisterFragment
import com.example.bookingagent.screens.register.RegisterRoutes
import com.example.bookingagent.screens.reservations.details.ReservationDetailsFragment
import com.example.bookingagent.screens.reservations.details.ReservationDetailsRoutes
import com.example.bookingagent.screens.reservations.list.ReservationRoutes
import com.example.bookingagent.screens.reservations.list.ReservationsFragment
import com.example.bookingagent.screens.rooms.add.AddRoomFragment
import com.example.bookingagent.screens.rooms.add.AddRoomRoutes
import com.example.bookingagent.screens.rooms.details.RoomDetailsFragment
import com.example.bookingagent.screens.rooms.details.RoomDetailsRoutes
import com.example.bookingagent.screens.rooms.edit.EditRoomFragment
import com.example.bookingagent.screens.rooms.edit.EditRoomRoutes
import com.example.bookingagent.screens.rooms.list.RoomsFragment
import com.example.bookingagent.screens.rooms.list.RoomsRoutes
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
	internal abstract fun bindAccommodationsRoutes(accommodationRoutes: AccommodationsRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(ReservationsFragment::class)
	internal abstract fun bindReservationsRoutes(reservationRoutes: ReservationRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(MessagesFragment::class)
	internal abstract fun bindMessagesRoutes(messagesRoutes: MessagesRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(ProfileFragment::class)
	internal abstract fun bindProfileRoutes(profileRoutes: ProfileRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(AccommodationDetailsFragment::class)
	internal abstract fun bindAccommodationDetailsRoutes(accommodationDetailsRoutes: AccommodationDetailsRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(EditAccommodationFragment::class)
	internal abstract fun bindAccommodationEditRoutes(editAccommodationRoutes: EditAccommodationRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(AddRoomFragment::class)
	internal abstract fun bindAddRoomRoutes(addRoomRoutes: AddRoomRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(RoomDetailsFragment::class)
	internal abstract fun bindRoomDetailsRoutes(roomDetailsRoutes: RoomDetailsRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(EditRoomFragment::class)
	internal abstract fun bindEditRoomRoutes(editRoomRoutes: EditRoomRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(RoomsFragment::class)
	internal abstract fun bindRoomsRoutes(roomsRoutes: RoomsRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(ReservationDetailsFragment::class)
	internal abstract fun bindReservationDetailsRoutes(reservationDetailsRoutes: ReservationDetailsRoutes): Routes

	@Binds
	@IntoMap
	@RouteKey(MessageThreadFragment::class)
	internal abstract fun bindMessageThreadRoutes(messageThreadRoutes: MessageThreadRoutes): Routes

}
