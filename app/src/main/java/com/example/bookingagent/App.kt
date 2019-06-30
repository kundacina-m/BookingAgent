package com.example.bookingagent

import android.content.Context
import androidx.multidex.MultiDex
import com.example.bookingagent.data.repository.AccommodationRepository
import com.example.bookingagent.data.repository.MessagesRepository
import com.example.bookingagent.data.repository.ReservationRepository
import com.example.bookingagent.data.repository.RoomRepository
import com.example.bookingagent.di.component.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var accommodationRepository: AccommodationRepository
    @Inject
    lateinit var messagesRepository: MessagesRepository
    @Inject
    lateinit var roomRepository: RoomRepository
    @Inject
    lateinit var reservationRepository: ReservationRepository

    companion object {
        lateinit var app: App
        fun getInstance() = app
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.factory().create(this)

    override fun onCreate() {
        super.onCreate()
        app = this

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }


}