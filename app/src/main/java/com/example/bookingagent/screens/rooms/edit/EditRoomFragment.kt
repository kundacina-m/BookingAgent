package com.example.bookingagent.screens.rooms.edit

import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.fragment.navArgs
import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class EditRoomFragment : BaseFragment<EditRoomViewModel, EditRoomRoutes>() {

	private val args: EditRoomFragmentArgs by navArgs()

	override fun getLayoutId(): Int = R.layout.fragment_edit_room

	override fun setObservers() {}

	override fun initView() {
		actionBarSetup()
	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = ""
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
	}

}
