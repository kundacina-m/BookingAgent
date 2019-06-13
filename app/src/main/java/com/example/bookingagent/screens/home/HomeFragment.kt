package com.example.bookingagent.screens.home

import base.BaseFragment
import com.example.bookingagent.R

class HomeFragment : BaseFragment<HomeViewModel,HomeRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_home

	override fun setObservers() {}

	override fun initView() {}
}