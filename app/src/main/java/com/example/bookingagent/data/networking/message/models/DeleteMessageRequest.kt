package com.example.bookingagent.data.networking.message.models

import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root
@Namespace(prefix = "res", reference = "http://xml/reservation")
@SoapRequest
data class DeleteMessageRequest(
	@field:Element(name = "res:messageId") val messageId: Int
)