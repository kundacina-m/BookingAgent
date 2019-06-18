package com.example.bookingagent.screens.accommodations.details.edit

import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.fragment.navArgs
import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etAddress
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etCity
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etDescription
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etLatitude
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etLongitude
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etName
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etNum
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etZipCode
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class EditAccommodationFragment : BaseFragment<EditAccommodationViewModel, EditAccommodationRoutes>() {

	private val args: EditAccommodationFragmentArgs by navArgs()

	override fun getLayoutId(): Int = R.layout.fragment_edit_accommodation

	override fun setObservers() {}

	override fun initView() {
		actionBarSetup()
		fillViewWithData()
	}

	private fun fillViewWithData() {
		etName.setText(args.name)
		etDescription.setText(args.description)
		etLatitude.setText(args.address.latitude.toString())
		etLongitude.setText(args.address.longitude.toString())
		etCity.setText(args.address.city)
		etZipCode.setText(args.address.zipCode.toString())
		etAddress.setText(args.address.street)
		etNum.setText(args.address.num.toString())

	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = args.name
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
	}

}