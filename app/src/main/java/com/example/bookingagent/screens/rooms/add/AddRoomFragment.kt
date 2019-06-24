package com.example.bookingagent.screens.rooms.add

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.screens.rooms.DialogAddSchedule
import com.example.bookingagent.screens.rooms.ImagesAdapter
import com.example.bookingagent.screens.rooms.ScheduleAdapter
import com.example.bookingagent.utils.FILE_CHOOSER_IMAGE
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.asString
import com.example.bookingagent.utils.toBase64
import kotlinx.android.synthetic.main.fragment_add_room.addRoomContainer
import kotlinx.android.synthetic.main.fragment_add_room.btAddImage
import kotlinx.android.synthetic.main.fragment_add_room.btAddSchedule
import kotlinx.android.synthetic.main.fragment_add_room.btSubmit
import kotlinx.android.synthetic.main.fragment_add_room.etBedNums
import kotlinx.android.synthetic.main.fragment_add_room.etFloor
import kotlinx.android.synthetic.main.fragment_add_room.etNumber
import kotlinx.android.synthetic.main.fragment_add_room.etPrice
import kotlinx.android.synthetic.main.fragment_add_room.rvImages
import kotlinx.android.synthetic.main.fragment_add_room.rvSchedule
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top
import java.util.GregorianCalendar
import kotlin.random.Random

class AddRoomFragment : BaseFragment<AddRoomViewModel, AddRoomRoutes>() {

	private val args: AddRoomFragmentArgs by navArgs()

	private val scheduleAdapter by lazy { ScheduleAdapter() }
	private val imagesAdapter by lazy { ImagesAdapter() }

	override fun getLayoutId(): Int = R.layout.fragment_add_room

	override fun setObservers() =
		with(viewModel) {

			images.observe(this@AddRoomFragment, Observer {
				imagesAdapter.setData(it)
			})

			schedule.observe(this@AddRoomFragment, Observer {
				scheduleAdapter.setData(it)
			})

			roomAddedResponse.observe(this@AddRoomFragment, Observer {
				when (it) {
					is OnSuccess -> navigation.navigateToRoomsList()
					is OnError -> Log.d(TAG, "setObservers: ERROR")
				}
			})
		}

	override fun initView() {
		actionBarSetup()
		setOnClickListeners()

		setRecyclerView()
	}

	private fun setRecyclerView() {
		rvSchedule.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@AddRoomFragment.scheduleAdapter
		}

		rvImages.apply {
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
			adapter = this@AddRoomFragment.imagesAdapter
		}
	}

	private fun setOnClickListeners() {
		btAddImage.setOnClickListener {
			openFileChooser()
		}

		btAddSchedule.setOnClickListener {
			DialogAddSchedule.build(context!!) {
				confirmedTag = this@AddRoomFragment::addSchedule
			}.show()

			addRoomContainer.clearFocus()
		}

		btSubmit.setOnClickListener {
			viewModel.addRoom(args.accId, createRoom())
		}
	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = ""
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		inflater.inflate(R.menu.confirm_action, menu)
		setOnMenuItemClickListener(menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setOnMenuItemClickListener(menu: Menu) =
		menu.findItem(R.id.confirmAction).setOnMenuItemClickListener {
			true
		}

	private fun createRoom() =
		Room(
			id = Random.nextInt(),
			roomNum = etNumber.asString()?.toIntOrNull(),
			floor = etFloor.asString()?.toIntOrNull(),
			bedNums = etBedNums.asString()?.toIntOrNull(),
			price = etPrice.asString()?.toFloatOrNull(),
			occupied = arrayListOf(),
			comments = arrayListOf(),
			images = ArrayList(imagesAdapter.getData()),
			schedule = ArrayList(scheduleAdapter.getData())
		)

	private fun addSchedule(checkIn: GregorianCalendar, checkOut: GregorianCalendar, price: Float) =
		scheduleAdapter.getData().toMutableList().apply {
			add(ScheduleUnit(checkIn, checkOut, price))
		}.run { viewModel.schedule.postValue(this) }

	private fun addImage(encodedImage: String) =
		imagesAdapter.getData().toMutableList().apply {
			add(encodedImage)
		}.run { viewModel.images.postValue(this) }

	// Choose image from storage

	private fun openFileChooser() =
		with(Intent()) {
			type = "image/*"
			action = Intent.ACTION_GET_CONTENT
			startActivityForResult(this, FILE_CHOOSER_IMAGE)
		}

	// On image chosen

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		data?.data?.run {
			if (requestCode == FILE_CHOOSER_IMAGE && resultCode == RESULT_OK)
				activity?.contentResolver?.openInputStream(this).also {
					addImage(BitmapFactory.decodeStream(it).toBase64())
				}
		}
	}
}
