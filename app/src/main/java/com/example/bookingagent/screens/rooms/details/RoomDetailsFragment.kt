package com.example.bookingagent.screens.rooms.details

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.screens.rooms.ImagesAdapter
import com.example.bookingagent.screens.rooms.ScheduleAdapter
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_room_details.tvNumber
import kotlinx.android.synthetic.main.fragment_room_details.*

import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class RoomDetailsFragment : BaseFragment<RoomDetailsViewModel, RoomDetailsRoutes>() {

	private val imagesAdapter by lazy { ImagesAdapter() }
	private val scheduleAdapter by lazy { ScheduleAdapter() }

	private val args: RoomDetailsFragmentArgs by navArgs()

	override fun getLayoutId(): Int = R.layout.fragment_room_details

	override fun setObservers() {
		viewModel.images.observe(this, Observer {
			imagesAdapter.setData(it)
		})

		viewModel.schedule.observe(this, Observer {
			scheduleAdapter.setData(it)
		})

		viewModel.deleteStatus.observe(this, Observer {
			when (it) {
				is OnSuccess -> navigation.navigateToRooms()
				is OnError -> Log.d(TAG, "setObservers: ERROR")
			}
		})
	}

	override fun initView() {
		actionBarSetup()
		populateViewWithData()
		setupRecyclerViews()
	}

	private fun setupRecyclerViews() {
		rvImages.apply {
			layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
			adapter = imagesAdapter
		}

		rvSchedule.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = scheduleAdapter
		}
	}

	private fun populateViewWithData() {
		with(args.room){
			tvNumber.text = roomNum.toString()
			tvFloor.text = floor.toString()
			tvPrice.text = price.toString()
			tvBedsNum.text = bedNums.toString()
			imagesAdapter.setData(images!!)
			scheduleAdapter.setData(schedule!!)
		}


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
			viewModel.deleteRoom(args.room.id)
			true
		}
	}

}
