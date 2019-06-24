package com.example.bookingagent.data.networking.reservation.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "res", reference = "http://xml/reservation")
@SoapResponse
class GetReservationResponse