package com.example.bookingagent.data.networking.helloworld.models

import com.example.soap_annotations.SoapRequest
import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root


@SoapRequest
@Root
@Namespace(prefix = "prob", reference = "http://proba.com")
data class HelloWorldRequest(
    @field:Element(name = "prob:name") var name: String? = null
)
