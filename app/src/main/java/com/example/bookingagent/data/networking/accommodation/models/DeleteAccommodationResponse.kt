package com.example.bookingagent.data.networking.accommodation.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "ns2", reference = "http://xml/accommodation")
@SoapResponse
class DeleteAccommodationResponse