package com.example.bookingagent.utils

import com.example.bookingagent.data.db.entities.Accommodation

object FakeData {

	val accommodationData = arrayListOf(
		Accommodation(1, "Prvi hotel", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", 1, "Hotel", 4, 40, arrayListOf("slika1", "slika2")),
		Accommodation(2, "Drugi hotel", "Los hotel", 2, "Hotel", 6, 40, arrayListOf("slika1", "slika2")),
		Accommodation(3, "Treci hotel", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum", 3, "Hotel", 7, 40, arrayListOf("slika1", "slika2")),
		Accommodation(4, "Cetvrti hotel", "Los hotel", 4, "Hotel", 2, 40, arrayListOf("slika1", "slika2")),
		Accommodation(5, "Peti hotel", "Dobar hotel", 5, "Hotel", 4, 40, arrayListOf("slika1", "slika2")),
		Accommodation(6, "Sesti hotel", "Dobar hotel", 6, "Hotel", 3, 40, arrayListOf("slika1", "slika2")),
		Accommodation(7, "Sedmi hotel", "Dobar hotel", 7, "Hotel", 7, 40, arrayListOf("slika1", "slika2")),
		Accommodation(8, "Osmi hotel", "Los hotel", 8, "Hotel", 9, 40, arrayListOf("slika1", "slika2")),
		Accommodation(9, "Deveti hotel", "Dobar hotel", 9, "Hotel", 2, 40, arrayListOf("slika1", "slika2")),
		Accommodation(10, "Deseti hotel", "Los hotel", 10, "Hotel", 1, 40, arrayListOf("slika1", "slika2")),
		Accommodation(11, "11 hotel", "Dobar hotel", 11, "Hotel", 3, 40, arrayListOf("slika1", "slika2"))
	)
}