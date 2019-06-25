package com.example.bookingagent.screens.rooms.details

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.screens.rooms.ImagesAdapter
import com.example.bookingagent.screens.rooms.ScheduleAdapter
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_room_details.rvImages
import kotlinx.android.synthetic.main.fragment_room_details.rvSchedule
import kotlinx.android.synthetic.main.fragment_room_details.tvBedsNum
import kotlinx.android.synthetic.main.fragment_room_details.tvFloor
import kotlinx.android.synthetic.main.fragment_room_details.tvNumber
import kotlinx.android.synthetic.main.fragment_room_details.tvPrice
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class RoomDetailsFragment : BaseFragment<RoomDetailsViewModel, RoomDetailsRoutes>() {

	private val imagesAdapter by lazy { ImagesAdapter() }
	private val scheduleAdapter by lazy { ScheduleAdapter() }
	private lateinit var roomEntity: RoomEntity

	private val args: RoomDetailsFragmentArgs by navArgs()

	override fun getLayoutId(): Int = R.layout.fragment_room_details

	override fun setObservers() {
		with(viewModel) {
			room.observe(this@RoomDetailsFragment, Observer {
				when (it) {
					is OnSuccess -> onDetailsFetched(it.item)
					is OnError -> Log.d(TAG, "setObservers: ERROR")
				}
			})

			images.observe(this@RoomDetailsFragment, Observer {
				imagesAdapter.setData(it)
			})

			schedule.observe(this@RoomDetailsFragment, Observer {
				scheduleAdapter.setData(it)
			})

			deleteStatus.observe(this@RoomDetailsFragment, Observer {
				when (it) {
					is OnSuccess -> navigation.navigateToRooms()
					is OnError -> Log.d(TAG, "setObservers: ERROR")
				}
			})
		}
	}

	private fun onDetailsFetched(roomEntity: RoomEntity) {
		this.roomEntity = roomEntity
		populateViewWithData(roomEntity)
	}

	override fun initView() {
		setActionBar(toolbar_top, true)
		setupRecyclerViews()
		fetchRoomDetails()
	}

	private fun fetchRoomDetails() =
		viewModel.getRoomById(args.roomId)

	private fun setupRecyclerViews() {
		rvImages.apply {
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
			adapter = imagesAdapter
		}

		rvSchedule.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = scheduleAdapter
		}
	}

	private fun populateViewWithData(roomEntity: RoomEntity) =
		with(roomEntity) {
			tvNumber.text = roomNum.toString()
			tvFloor.text = floor.toString()
			tvPrice.text = price.toString()
			tvBedsNum.text = bedNums.toString()
			images?.run { imagesAdapter.setData(this) }
			schedule?.run { scheduleAdapter.setData(this) }
		}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		inflater.inflate(R.menu.room_menu, menu)
		setOnMenuItemClickListener(menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setOnMenuItemClickListener(menu: Menu) {

		menu.findItem(R.id.editRoom).setOnMenuItemClickListener {
			navigation.navigateToEdit(roomEntity)
			true
		}

		menu.findItem(R.id.deleteRoom).setOnMenuItemClickListener {
			viewModel.deleteRoom(roomEntity.id)
			true
		}
	}

}
