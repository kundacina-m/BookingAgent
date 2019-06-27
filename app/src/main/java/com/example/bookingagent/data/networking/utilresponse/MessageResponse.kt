package com.example.bookingagent.data.networking.utilresponse

import org.simpleframework.xml.Element

data class MessageResponse(
    @field:Element(name = "id") var id: Int = 0,
    @field:Element(name = "korisnickoIme") var firstname: String = "",
    @field:Element(name = "korisnickoPrezime") var lastname: String = "",
    @field:Element(name = "text") var content: String = "",
    @field:Element(name = "emailAgenta") var agentEmail: String = "",
    @field:Element(name = "sender", required = false) var sender: String = ""
)