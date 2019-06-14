package com.example.bookingagent.screens.accommodations.details.edit

import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.fragment.navArgs
import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.fragment_accommodation_edit.tvName
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class AccommodationEditFragment : BaseFragment<AccommodationEditViewModel, AccommodationEditRoutes>() {

	private val args: AccommodationEditFragmentArgs by navArgs()

	override fun getLayoutId(): Int = R.layout.fragment_accommodation_edit

	override fun setObservers() {}

	override fun initView() {
		actionBarSetup()
		tvName.text = args.name
	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top,true)
		actionBar?.title = args.name
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
	}

}