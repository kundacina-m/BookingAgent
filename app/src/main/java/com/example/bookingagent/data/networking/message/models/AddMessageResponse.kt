package com.example.bookingagent.data.networking.message.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(strict = false)
@Namespace(prefix = "ns2", reference = "http://xml/reservation")
@SoapResponse
data class AddMessageResponse(
    @field:Element(name = "messageId") var messageId: Int = 0
)