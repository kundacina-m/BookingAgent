package com.example.bookingagent.utils

import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.db.entities.Address
import com.example.bookingagent.data.networking.accommodation.models.AddAccommodationRequest
import com.example.bookingagent.data.networking.common.AddressSOAP

fun AddAccommodationRequest.toModel(idAccommodation: Int, idAddress: Int): Accommodation =
	Accommodation(
		id = idAccommodation,
		name = this.name,
		description = this.description,
		type = this.type,
		beds = this.bedsNum,
		cancelingFee = this.cancelingFee,
		address = idAddress,
		images = this.images
	)

fun AddressSOAP.toModel(idAddress: Int): Address =
	Address(
		id = idAddress,
		latitude = this.latitude,
		longitude = this.longitude,
		city = this.city,
		num = this.num,
		street = this.street,
		zipCode = this.zipCode
	)
