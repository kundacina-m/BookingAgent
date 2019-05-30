package com.example.bookingagent.screens.main

import android.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import base.BaseActivity
import com.example.bookingagent.R
import dagger.android.AndroidInjector
import javax.inject.Inject

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
