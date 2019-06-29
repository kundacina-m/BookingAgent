package com.example.bookingagent.screens.rooms.edit

import android.app.Activity
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
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.data.model.OccupiedTime
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.screens.rooms.*
import com.example.bookingagent.utils.FILE_CHOOSER_IMAGE
import com.example.bookingagent.utils.RequestError.HttpError
import com.example.bookingagent.utils.RequestError.UnknownError
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.asString
import com.example.bookingagent.utils.compressBase64
import com.example.bookingagent.utils.toBase64
import kotlinx.android.synthetic.main.fragment_edit_room.*
import kotlinx.android.synthetic.main.fragment_edit_room.btAddImage
import kotlinx.android.synthetic.main.fragment_edit_room.btAddSchedule
import kotlinx.android.synthetic.main.fragment_edit_room.etBedNums
import kotlinx.android.synthetic.main.fragment_edit_room.etFloor
import kotlinx.android.synthetic.main.fragment_edit_room.etNumber
import kotlinx.android.synthetic.main.fragment_edit_room.etPrice
import kotlinx.android.synthetic.main.fragment_edit_room.focusContainer
import kotlinx.android.synthetic.main.fragment_edit_room.rvImages
import kotlinx.android.synthetic.main.fragment_edit_room.rvNotAvailable
import kotlinx.android.synthetic.main.fragment_edit_room.rvSchedule
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top
import java.util.GregorianCalendar

class EditRoomFragment : BaseFragment<EditRoomViewModel, EditRoomRoutes>() {

    private val args: EditRoomFragmentArgs by navArgs()
    private val imagesAdapter by lazy { ImagesAdapter() }
    private val scheduleAdapter by lazy { ScheduleAdapter() }
    private val notAvailableAdapter by lazy { NotAvailableAdapter() }


    override fun getLayoutId(): Int = R.layout.fragment_edit_room

    override fun setObservers() =

        with(viewModel) {

            images.observe(this@EditRoomFragment, Observer {
                imagesAdapter.setData(it)
            })

            schedule.observe(this@EditRoomFragment, Observer {
                scheduleAdapter.setData(it)
            })

            notAvailable.observe(this@EditRoomFragment, Observer {
                notAvailableAdapter.setData(it)
            })

            editStatus.observe(this@EditRoomFragment, Observer {
                when (it) {
                    is OnSuccess -> navigation.navigateToRooms()
                    is OnError -> when (it.error) {
                        is UnknownError -> Log.d(TAG, "setObservers: ${it.error.t}")
                        is HttpError -> Log.d(TAG, "setObservers: ${it.error.message}")
                    }
                }
            })
        }

    override fun initView() {
        setActionBar(toolbar_top, true)
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
        rvNotAvailable.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@EditRoomFragment.notAvailableAdapter
        }

    }

    private fun populateViewWithData() =
        with(args.roomEntity) {
            etNumber.setText(roomNum.toString())
            etFloor.setText(floor.toString())
            etBedNums.setText(bedNums.toString())
            etPrice.setText(price.toString())
            viewModel.images.postValue(images)
            viewModel.schedule.postValue(schedule)
        }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.confirm_action, menu)
        setOnMenuItemClickListener(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setOnMenuItemClickListener(menu: Menu) {
        menu.findItem(R.id.confirmAction).setOnMenuItemClickListener {
            checkIfAllFilled()
            true
        }
    }

    private fun setOnClickListeners() {
        btAddImage.setOnClickListener {
            openFileChooser()
        }

        btAddSchedule.setOnClickListener {
            AddScheduleDialog.build(context!!) {
                confirmedTag = this@EditRoomFragment::addSchedule
            }.show()

            focusContainer.clearFocus()
        }

        btAddNotAvailable.setOnClickListener {
            AddNotAvailableDialog.build(context!!) {
                confirmedTag = this@EditRoomFragment::addNotAvailable
            }.show()
            focusContainer.clearFocus()

        }

    }

    private fun createRoom() =
        RoomEntity(
            id = args.roomEntity.id,
            roomNum = etNumber.asString().toInt(),
            floor = etFloor.asString().toInt(),
            bedNums = etBedNums.asString().toInt(),
            price = etPrice.asString().toFloat(),
            occupied = ArrayList(notAvailableAdapter.getData()),
            comments = arrayListOf(),
            images = ArrayList(imagesAdapter.getData()),
            schedule = ArrayList(scheduleAdapter.getData())
        )

    private fun addNotAvailable(checkIn: GregorianCalendar, checkOut: GregorianCalendar) =
        notAvailableAdapter.getData().toMutableList().apply {
            add(OccupiedTime(checkIn, checkOut))
        }.run { viewModel.notAvailable.postValue(this) }

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
            if (requestCode == FILE_CHOOSER_IMAGE && resultCode == Activity.RESULT_OK)
                activity?.contentResolver?.openInputStream(this).also {
                    val image = BitmapFactory.decodeStream(it).toBase64()
                    addImage(image.compressBase64())
                }
        }
    }

    private fun checkIfAllFilled() {
        when {
            imagesAdapter.getData().isEmpty() -> showToast("You need to add at least one image!")
            etNumber.asString().isEmpty() -> showToast("You must enter room number!")
            etFloor.asString().isEmpty() -> showToast("You must enter floor number!")
            etBedNums.asString().isEmpty() -> showToast("You must enter beds number!")
            etPrice.asString().isEmpty() -> showToast("You must enter price!")
            else -> viewModel.editRoom(createRoom())

        }
    }

}
