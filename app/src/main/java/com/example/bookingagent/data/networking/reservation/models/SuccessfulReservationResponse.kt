package com.example.bookingagent.data.networking.reservation.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "ns2", reference = "http://xml/reservation")
@SoapResponse
class SuccessfulReservationResponse