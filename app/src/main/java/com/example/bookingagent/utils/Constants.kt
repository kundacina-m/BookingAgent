package com.example.bookingagent.utils

const val BASE_URL = "http://192.168.0.108:8080/service/"

const val FILE_CHOOSER_IMAGE = 1

object Cache {

    var pass = ""

    fun cachePass(password: String) {
        pass = password
    }
}

object ApiHeaders {

    val map = hashMapOf(
        Pair("Authorization", ""),
        Pair("Content-Type", "text/xml;charset=utf-8")
    )

    fun addToken(token: String) {
        map["Authorization"] = token
    }

    fun removeToken() {
        map["Authorization"] = ""
    }
}