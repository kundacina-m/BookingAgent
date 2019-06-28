package com.example.bookingagent.screens.profile

import androidx.lifecycle.Observer
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.UserEntity
import com.example.bookingagent.utils.WrappedResponse
import kotlinx.android.synthetic.main.fragment_profile.tvCity
import kotlinx.android.synthetic.main.fragment_profile.tvEmail
import kotlinx.android.synthetic.main.fragment_profile.tvFullName
import kotlinx.android.synthetic.main.fragment_profile.tvStreet
import kotlinx.android.synthetic.main.fragment_profile.tvUsername

class ProfileFragment : BaseFragment<ProfileViewModel, ProfileRoutes>() {

	override fun getLayoutId(): Int = R.layout.fragment_profile

	override fun setObservers() {

		viewModel.userInfo.observe(this, Observer {
			when (it) {
				is WrappedResponse.OnSuccess -> fillViewWithData(it.item)
			}
		})
	}

	private fun fillViewWithData(userDetails: UserEntity) {
		tvUsername.text = userDetails.username
		tvFullName.text = (userDetails.firstname + " " + userDetails.lastname)
		tvEmail.text = userDetails.email
		tvStreet.text = (userDetails.address?.street + " " + userDetails.address?.num)
		tvCity.text = userDetails.address?.city
	}

	override fun initView() {

		viewModel.getUserInfo()
	}

}
