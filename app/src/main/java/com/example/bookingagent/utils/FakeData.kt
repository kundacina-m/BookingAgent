package com.example.bookingagent.utils

import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address
import com.example.bookingagent.data.db.entities.Image
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.db.entities.ScheduleUnit
import com.example.bookingagent.data.db.entities.Service
import java.util.GregorianCalendar

object FakeData {

	val accommodationData = arrayListOf(
		Accommodation(
			1,
			"Prvi hotel",
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
			Address(1, 2132, 3211, "Novi Sad", 82300, "Bulevar Oslobodjenja", 94),
			"Hotel",
			250,
			4.0f,
			"category",
			arrayListOf(Service(1, "usluga", "losa", 5), Service(1, "usluga", "losa", 5)),
			arrayListOf(Room(1, 2, 3, 4, 5, true, arrayListOf("los", "dobar"), arrayListOf(Image(1, "1")), arrayListOf
			(ScheduleUnit(1, GregorianCalendar(), GregorianCalendar(), 2.0f))))
		),
		Accommodation(
			1,
			"Prvi hotel",
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
			Address(1, 2132, 3211, "Novi Sad", 82300, "Bulevar Oslobodjenja", 94),
			"Hotel",
			250,
			4.0f,
			"category",
			arrayListOf(Service(1, "usluga", "losa", 5), Service(1, "usluga", "losa", 5)),
			arrayListOf(Room(1, 2, 3, 4, 5, true, arrayListOf("los", "dobar"), arrayListOf(Image(1, "1")), arrayListOf
			(ScheduleUnit(1, GregorianCalendar(), GregorianCalendar(), 2.0f))))
		), Accommodation(
		1,
		"Prvi hotel",
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
		Address(1, 2132, 3211, "Novi Sad", 82300, "Bulevar Oslobodjenja", 94),
		"Hotel",
		250,
		4.0f,
		"category",
		arrayListOf(Service(1, "usluga", "losa", 5), Service(1, "usluga", "losa", 5)),
		arrayListOf(Room(1, 2, 3, 4, 5, true, arrayListOf("los", "dobar"), arrayListOf(Image(1, "1")), arrayListOf
		(ScheduleUnit(1, GregorianCalendar(), GregorianCalendar(), 2.0f))))
	), Accommodation(
		1,
		"Prvi hotel",
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
		Address(1, 2132, 3211, "Novi Sad", 82300, "Bulevar Oslobodjenja", 94),
		"Hotel",
		250,
		4.0f,
		"category",
		arrayListOf(Service(1, "usluga", "losa", 5), Service(1, "usluga", "losa", 5)),
		arrayListOf(Room(1, 2, 3, 4, 5, true, arrayListOf("los", "dobar"), arrayListOf(Image(1, "1")), arrayListOf
		(ScheduleUnit(1, GregorianCalendar(), GregorianCalendar(), 2.0f))))
	), Accommodation(
		1,
		"Prvi hotel",
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
		Address(1, 2132, 3211, "Novi Sad", 82300, "Bulevar Oslobodjenja", 94),
		"Hotel",
		250,
		4.0f,
		"category",
		arrayListOf(Service(1, "usluga", "losa", 5), Service(1, "usluga", "losa", 5)),
		arrayListOf(Room(1, 2, 3, 4, 5, true, arrayListOf("los", "dobar"), arrayListOf(Image(1, "1")), arrayListOf
		(ScheduleUnit(1, GregorianCalendar(), GregorianCalendar(), 2.0f))))
	), Accommodation(
		1,
		"Prvi hotel",
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
		Address(1, 2132, 3211, "Novi Sad", 82300, "Bulevar Oslobodjenja", 94),
		"Hotel",
		250,
		4.0f,
		"category",
		arrayListOf(Service(1, "usluga", "losa", 5), Service(1, "usluga", "losa", 5)),
		arrayListOf(Room(1, 2, 3, 4, 5, true, arrayListOf("los", "dobar"), arrayListOf(Image(1, "1")), arrayListOf
		(ScheduleUnit(1, GregorianCalendar(), GregorianCalendar(), 2.0f))))
	), Accommodation(
		1,
		"Prvi hotel",
		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
		Address(1, 2132, 3211, "Novi Sad", 82300, "Bulevar Oslobodjenja", 94),
		"Hotel",
		250,
		4.0f,
		"category",
		arrayListOf(Service(1, "usluga", "losa", 5), Service(1, "usluga", "losa", 5)),
		arrayListOf(Room(1, 2, 3, 4, 5, true, arrayListOf("los", "dobar"), arrayListOf(Image(1, "1")), arrayListOf
		(ScheduleUnit(1, GregorianCalendar(), GregorianCalendar(), 2.0f))))
	))
}