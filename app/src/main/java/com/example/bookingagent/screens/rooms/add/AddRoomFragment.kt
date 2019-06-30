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
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.data.model.OccupiedTime
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.screens.rooms.*
import com.example.bookingagent.utils.*
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_add_room.*
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top
import java.util.GregorianCalendar
import kotlin.random.Random

class AddRoomFragment : BaseFragment<AddRoomViewModel, AddRoomRoutes>() {

    private val args: AddRoomFragmentArgs by navArgs()

    private val scheduleAdapter by lazy { ScheduleAdapter() }
    private val imagesAdapter by lazy { ImagesAdapter() }
    private val notAvailableAdapter by lazy { NotAvailableAdapter() }

    override fun getLayoutId(): Int = R.layout.fragment_add_room

    override fun setObservers() =
        with(viewModel) {

            images.observe(this@AddRoomFragment, Observer {
                imagesAdapter.setData(it)
            })

            schedule.observe(this@AddRoomFragment, Observer {
                scheduleAdapter.setData(it)
            })

            notAvailable.observe(this@AddRoomFragment, Observer {
                notAvailableAdapter.setData(it)
            })

            roomAddedResponse.observe(this@AddRoomFragment, Observer {
                when (it) {
                    is OnSuccess -> navigation.navigateToRoomsList()
                    is OnError -> handleError(it.error)
                }
            })
        }

    override fun initView() {
        setActionBar(toolbar_top, true)
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

        rvNotAvailable.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@AddRoomFragment.notAvailableAdapter
        }
    }

    private fun setOnClickListeners() {
        btAddImage.setOnClickListener {
            openFileChooser()
        }

        btAddSchedule.setOnClickListener {
            AddScheduleDialog.build(context!!) {
                confirmedTag = this@AddRoomFragment::addSchedule
            }.show()

            focusContainer.clearFocus()
        }

        btAddNotAvailable.setOnClickListener {
            AddNotAvailableDialog.build(context!!) {
                confirmedTag = this@AddRoomFragment::addNotAvailable
            }.show()
            focusContainer.clearFocus()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.confirm_action, menu)
        setOnMenuItemClickListener(menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setOnMenuItemClickListener(menu: Menu) =
        menu.findItem(R.id.confirmAction).setOnMenuItemClickListener {
            checkIfAllFilled()
            true
        }

    private fun createRoom() =
        RoomEntity(
            id = Random.nextInt(),
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
            if (requestCode == FILE_CHOOSER_IMAGE && resultCode == RESULT_OK)
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
            else -> viewModel.addRoom(args.accId, createRoom())
        }
    }

    private fun handleError(error: RequestError) {
        when (error){
            is RequestError.NoInternetError -> showToast("Can't complete request, no Internet connection")
            is RequestError.HttpError -> showToast("Bad request")
            is UnknownError -> showToast("There is issue with server, request was not processed")
        }
    }
}
