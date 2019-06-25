package com.example.bookingagent.screens.rooms.list

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
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

	override fun setObservers() =
		viewModel.rooms.observe(this, Observer {
			when (it) {
				is OnSuccess -> adapter.setData(it.item)
				is OnError -> Log.d(TAG, "setObservers: ERROR")
			}
		})

	override fun initView() {
		actionBarSetup()
		setupRecyclerView()
		setClickListeners()
		fetchRooms()
	}

	private fun fetchRooms() =
		viewModel.getAllRoomsByAccId(args.accId)

	private fun setClickListeners() =
		fabAddRoom.setOnClickListener {
			navigation.navigateToAddRoom(args.accId)
		}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
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

	private fun itemSelected(roomEntity: RoomEntity) =
		navigation.navigateToSelectedRoom(args.accId, roomEntity.id)

}
