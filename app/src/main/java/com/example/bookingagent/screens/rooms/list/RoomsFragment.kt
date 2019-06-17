package com.example.bookingagent.screens.rooms.list

import android.view.Menu
import android.view.MenuInflater
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Room
import kotlinx.android.synthetic.main.fragment_rooms.fabAddRoom
import kotlinx.android.synthetic.main.fragment_rooms.rvRooms
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class RoomsFragment : BaseFragment<RoomsViewModel, RoomsRoutes>() {

	private val adapter by lazy {
		RoomsAdapter().apply {
			onItemClickListener = this@RoomsFragment::itemSelected
		}
	}

	private val args: RoomsFragmentArgs by navArgs()

	override fun getLayoutId(): Int = R.layout.fragment_rooms

	override fun setObservers() {}

	override fun initView() {
		actionBarSetup()
		setupRecyclerView()

		fabAddRoom.setOnClickListener {
			navigation.navigateToAddRoom()
		}

		adapter.setData(args.rooms.toList())
	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = ""
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setupRecyclerView() =
		rvRooms.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@RoomsFragment.adapter
		}


	private fun itemSelected(room: Room) =
		navigation.navigateToSelectedRoom(room)


}
