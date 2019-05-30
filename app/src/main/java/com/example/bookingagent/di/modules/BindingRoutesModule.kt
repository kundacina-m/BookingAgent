package com.example.bookingagent.di.modules

import com.example.bookingagent.di.scopes.ActivityScope
import com.example.bookingagent.screens.home.HomeRoutes
import com.example.bookingagent.screens.login.LoginRoutes
import com.example.bookingagent.screens.main.MainActivity
import com.example.bookingagent.screens.register.RegisterRoutes
import com.example.bookingagent.utils.NavigationController
import dagger.Module
import dagger.Provides

@Module
class BindingRoutesModule {

    @ActivityScope
    @Provides
    fun provideNavController(mainActivity: MainActivity) : NavigationController = NavigationController(mainActivity)

    @ActivityScope
    @Provides
    fun provideLoginRoutes(navigationController: NavigationController): LoginRoutes{
        return LoginRoutes(navigationController)
    }

    @ActivityScope
    @Provides
    fun provideHomeRoutes(): HomeRoutes{
        return HomeRoutes()
    }

    @ActivityScope
    @Provides
    fun provideRegisterRoutes(): RegisterRoutes{
        return RegisterRoutes()
    }
}