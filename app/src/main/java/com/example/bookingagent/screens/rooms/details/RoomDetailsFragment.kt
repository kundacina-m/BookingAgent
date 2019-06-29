package com.example.bookingagent.screens.rooms.details

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.screens.rooms.ImagesAdapter
import com.example.bookingagent.screens.rooms.NotAvailableAdapter
import com.example.bookingagent.screens.rooms.ScheduleAdapter
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.asString
import com.example.bookingagent.utils.isGone
import com.example.bookingagent.utils.isHidden
import kotlinx.android.synthetic.main.fragment_room_details.*
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top
import kotlin.properties.Delegates

class RoomDetailsFragment : BaseFragment<RoomDetailsViewModel, RoomDetailsRoutes>() {

	private val imagesAdapter by lazy { ImagesAdapter() }
	private val scheduleAdapter by lazy { ScheduleDetailsAdapter() }
	private val commentsAdapter by lazy { CommentsAdapter() }
	private val notAvailableAdapter by lazy { NotAvailableAdapter() }
	private lateinit var roomEntity: RoomEntity

	private var showComments by Delegates.observable(false,{
			_, _, visibility ->
		handleCommentsVisibility(visibility)
	})

	private var showSchedule by Delegates.observable(false,{
			_, _, visibility ->
		handleScheduleVisibility(visibility)
	})

	private var showNotAvailable by Delegates.observable(false,{
		_, _, visibility ->
		handleNotAvailableVisibility(visibility)
	})

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

			deleteStatus.observe(this@RoomDetailsFragment, Observer {
				when (it) {
					is OnSuccess -> navigation.navigateToRooms()
					is OnError -> Log.d(TAG, "setObservers: ERROR")
				}
			})

			images.observe(this@RoomDetailsFragment, Observer {
				imagesAdapter.setData(it)
			})

			schedule.observe(this@RoomDetailsFragment, Observer {
				scheduleAdapter.setData(it)
				showSchedule = it.isNotEmpty()
			})

			comments.observe(this@RoomDetailsFragment, Observer {
				commentsAdapter.setData(it)
				showComments = it.isNotEmpty()
			})

			notAvailable.observe(this@RoomDetailsFragment, Observer {
				notAvailableAdapter.setData(it)
				showNotAvailable = it.isNotEmpty()
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

		rvComments.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = commentsAdapter
		}

		rvNotAvailable.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = notAvailableAdapter
		}
	}

	private fun populateViewWithData(roomEntity: RoomEntity) =
		with(roomEntity) {
			tvNumber.text = roomNum.toString()
			tvFloor.text = floor.toString()
			tvPrice.text = price.asString()
			tvBedsNum.text = bedNums.toString()
			images.run { imagesAdapter.setData(this) }
			schedule.run {
				scheduleAdapter.setData(this)
				showSchedule = this.isNotEmpty()
			}
			comments.run {
				commentsAdapter.setData(this)
				showComments = this.isNotEmpty()
			}
			occupied.run {
				notAvailableAdapter.setData(this)
				showNotAvailable = this.isNotEmpty()
			}
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

	private fun handleCommentsVisibility(visibility: Boolean) {
		rvComments.isGone(visibility)
		tvLabelComments.isGone(visibility)
	}
	private fun handleScheduleVisibility(visibility: Boolean) {
		rvSchedule.isGone(visibility)
		tvLabelSchedule.isGone(visibility)
	}
	private fun handleNotAvailableVisibility(visibility: Boolean) {
		rvNotAvailable.isGone(visibility)
		tvLabelNotAvailable.isGone(visibility)
	}

}
