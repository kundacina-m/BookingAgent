package com.example.bookingagent.screens.profile

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.UserEntity
import com.example.bookingagent.utils.WrappedResponse
import com.example.bookingagent.utils.ApiHeaders
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_main.*

class ProfileFragment : BaseFragment<ProfileViewModel, ProfileRoutes>() {

    override fun getLayoutId(): Int = R.layout.fragment_profile

    override fun setObservers() =
        viewModel.userInfo.observe(this, Observer {
            when (it) {
                is WrappedResponse.OnSuccess -> fillViewWithData(it.item)
                is WrappedResponse.OnError -> {
                    showToast("Error issued while fetching user info!")
                    Log.d("ERROR",it.error.toString())
                }
            }
        })

    override fun initView() {
        setActionBar(toolbar_top,false)
        fetchData()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun fetchData() =
        viewModel.getUserInfo(ApiHeaders.map["Authorization"])

    private fun fillViewWithData(userDetails: UserEntity) {
        tvUsername.text = userDetails.username
        tvFirstName.text = userDetails.firstname
        tvLastName.text = userDetails.lastname
        tvEmail.text = userDetails.email
        tvStreet.text = (userDetails.address.street + " " + userDetails.address?.num)
        tvCity.text = userDetails.address.city
    }
}
