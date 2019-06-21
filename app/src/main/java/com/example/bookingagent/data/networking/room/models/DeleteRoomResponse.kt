package com.example.bookingagent.data.networking.room.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "ns2", reference = "http://xml/accommodationResponse")
@SoapResponse
class DeleteRoomResponse