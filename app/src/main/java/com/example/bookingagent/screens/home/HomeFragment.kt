package com.example.bookingagent.screens.home

import base.BaseFragment
import com.example.bookingagent.R

class HomeFragment : BaseFragment<HomeViewModel,HomeRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_home
	override fun getClassTypeVM(): Class<HomeViewModel> = HomeViewModel::class.java

	override fun initView() {

	}
}