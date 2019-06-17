package com.example.bookingagent.screens.rooms.details

import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.fragment.navArgs
import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class RoomDetailsFragment : BaseFragment<RoomDetailsViewModel, RoomDetailsRoutes>() {

	private val args: RoomDetailsFragmentArgs by navArgs()

	override fun getLayoutId(): Int = R.layout.fragment_room_details

	override fun setObservers() {}

	override fun initView() {
		actionBarSetup()
		populateViewWithData()
	}

	private fun populateViewWithData() {

	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = ""
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		inflater.inflate(R.menu.room_menu, menu)
		setOnMenuItemClickListener(menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setOnMenuItemClickListener(menu: Menu) {

		menu.findItem(R.id.editRoom).setOnMenuItemClickListener {
			navigation.navigateToEdit(args.room)
			true
		}

		menu.findItem(R.id.deleteRoom).setOnMenuItemClickListener {
			true
		}
	}

}
