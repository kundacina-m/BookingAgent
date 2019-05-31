package com.example.bookingagent.di.routes

import base.BaseFragment
import com.example.bookingagent.Routes
import com.example.bookingagent.di.scopes.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope
class RoutesFactory
@Inject
constructor(private val routes: Map<Class<out BaseFragment<*, *>>, @JvmSuppressWildcards Provider<Routes>>)
	: IRoutesFactory {

	override fun get(fragmentClass: Class<out BaseFragment<*, *>>): Routes? =
		routes[fragmentClass]?.get()
}