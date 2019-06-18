package com.example.bookingagent.screens.rooms.edit

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Room
import com.example.bookingagent.data.db.entities.ScheduleUnit
import com.example.bookingagent.screens.rooms.ImagesAdapter
import com.example.bookingagent.screens.rooms.ScheduleAdapter
import com.example.bookingagent.screens.rooms.add.DialogAddSchedule
import com.example.bookingagent.utils.FILE_CHOOSER_IMAGE
import com.example.bookingagent.utils.asString
import com.example.bookingagent.utils.toBase64
import kotlinx.android.synthetic.main.fragment_edit_room.btAddImage
import kotlinx.android.synthetic.main.fragment_edit_room.btAddSchedule
import kotlinx.android.synthetic.main.fragment_edit_room.btSubmit
import kotlinx.android.synthetic.main.fragment_edit_room.editRoomContainer
import kotlinx.android.synthetic.main.fragment_edit_room.etBedNums
import kotlinx.android.synthetic.main.fragment_edit_room.etFloor
import kotlinx.android.synthetic.main.fragment_edit_room.etNumber
import kotlinx.android.synthetic.main.fragment_edit_room.etPrice
import kotlinx.android.synthetic.main.fragment_edit_room.rvImages
import kotlinx.android.synthetic.main.fragment_edit_room.rvSchedule
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top
import java.util.GregorianCalendar
import kotlin.random.Random

class EditRoomFragment : BaseFragment<EditRoomViewModel, EditRoomRoutes>() {

	private val args: EditRoomFragmentArgs by navArgs()
	private val imagesAdapter by lazy { ImagesAdapter() }
	private val scheduleAdapter by lazy { ScheduleAdapter() }

	override fun getLayoutId(): Int = R.layout.fragment_edit_room

	override fun setObservers() {
		viewModel.images.observe(this, Observer {
			imagesAdapter.setData(it)
		})

		viewModel.schedule.observe(this, Observer {
			scheduleAdapter.setData(it)
		})
	}

	override fun initView() {
		actionBarSetup()
		setRecyclerView()
		populateViewWithData()
		setOnClickListeners()
	}

	private fun setRecyclerView() {
		rvImages.apply {
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
			adapter = imagesAdapter
		}

		rvSchedule.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = scheduleAdapter
		}
	}

	private fun populateViewWithData() =
		with(args.room) {
			etNumber.setText(roomNum.toString())
			etFloor.setText(floor.toString())
			etBedNums.setText(bedNums.toString())
			etPrice.setText(price.toString())
			viewModel.images.postValue(images)
			viewModel.schedule.postValue(schedule)
		}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = ""
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setOnClickListeners() {
		btAddImage.setOnClickListener {
			openFileChooser()
		}

		btAddSchedule.setOnClickListener {
			DialogAddSchedule.build(context!!) {
				confirmedTag = this@EditRoomFragment::addSchedule
			}.show()

			editRoomContainer.clearFocus()
		}

		btSubmit.setOnClickListener {
			viewModel.editRoom(Room(
				id = Random.nextLong(),
				roomNum = etNumber.asString()?.toIntOrNull(),
				floor = etFloor.asString()?.toIntOrNull(),
				bedNums = etBedNums.asString()?.toIntOrNull(),
				price = etPrice.asString()?.toIntOrNull(),
				availability = true,
				comments = arrayListOf(),
				images = ArrayList(imagesAdapter.getData()),
				schedule = ArrayList(scheduleAdapter.getData())
			))
		}
	}

	private fun addSchedule(checkIn: GregorianCalendar, checkOut: GregorianCalendar, price: Float) =
		scheduleAdapter.getData().toMutableList().apply {
			add(ScheduleUnit(Random.nextInt(), checkIn, checkOut, price))
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
			if (requestCode == FILE_CHOOSER_IMAGE && resultCode == Activity.RESULT_OK)
				activity?.contentResolver?.openInputStream(this).also {
					addImage(BitmapFactory.decodeStream(it).toBase64())
				}
		}
	}

}
