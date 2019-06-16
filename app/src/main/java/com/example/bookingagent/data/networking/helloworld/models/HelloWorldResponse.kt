package com.example.bookingagent.data.networking.helloworld.models

import com.example.soap_annotations.SoapResponse
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root

@Root(strict = false)
@Namespace(prefix = "ns2", reference = "http://proba.com")
@SoapResponse
data class HelloWorldResponse(
    @field:Element(name = "hello") var name: String? = null
)
