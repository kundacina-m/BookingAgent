package com.example.bookingagent.screens.main

import androidx.navigation.NavController
import androidx.navigation.Navigation
import base.BaseActivity
import com.example.bookingagent.R

class MainActivity : BaseActivity() {

	private val navCtrl: NavController by lazy {
		Navigation.findNavController(this, R.id.nav_host_fragment)
	}

	override fun getViewLayout(): Int = R.layout.activity_main

	override fun initView() {}

	override fun onSupportNavigateUp(): Boolean =
		navCtrl.navigateUp()

	override fun onBackPressed() {
		super.onBackPressed()
		navCtrl.navigateUp()
	}
}
