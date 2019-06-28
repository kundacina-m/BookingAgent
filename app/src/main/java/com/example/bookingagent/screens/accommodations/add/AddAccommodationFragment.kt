package com.example.bookingagent.screens.accommodations.add

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.AccommodationEntity
import com.example.bookingagent.data.model.Address
import com.example.bookingagent.data.model.Service
import com.example.bookingagent.screens.accommodations.AddServiceDialog
import com.example.bookingagent.screens.accommodations.ServicesAdapter
import com.example.bookingagent.screens.rooms.ImagesAdapter
import com.example.bookingagent.utils.FILE_CHOOSER_IMAGE
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.asString
import com.example.bookingagent.utils.showToast
import com.example.bookingagent.utils.toBase64
import kotlinx.android.synthetic.main.fragment_add_accommodation.btAddImage
import kotlinx.android.synthetic.main.fragment_add_accommodation.btAddService
import kotlinx.android.synthetic.main.fragment_add_accommodation.etAddress
import kotlinx.android.synthetic.main.fragment_add_accommodation.etCancellingDays
import kotlinx.android.synthetic.main.fragment_add_accommodation.etCancellingFee
import kotlinx.android.synthetic.main.fragment_add_accommodation.etCity
import kotlinx.android.synthetic.main.fragment_add_accommodation.etDescription
import kotlinx.android.synthetic.main.fragment_add_accommodation.etLatitude
import kotlinx.android.synthetic.main.fragment_add_accommodation.etLongitude
import kotlinx.android.synthetic.main.fragment_add_accommodation.etName
import kotlinx.android.synthetic.main.fragment_add_accommodation.etNum
import kotlinx.android.synthetic.main.fragment_add_accommodation.etZipCode
import kotlinx.android.synthetic.main.fragment_add_accommodation.focusContainer
import kotlinx.android.synthetic.main.fragment_add_accommodation.rvImages
import kotlinx.android.synthetic.main.fragment_add_accommodation.rvServices
import kotlinx.android.synthetic.main.fragment_add_accommodation.sp_categories
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class AddAccommodationFragment : BaseFragment<AddAccommodationViewModel, AddAccommodationRoutes>() {

	private val servicesAdapter by lazy { ServicesAdapter() }
	private val imagesAdapter by lazy { ImagesAdapter() }

	override fun getLayoutId(): Int = R.layout.fragment_add_accommodation

	override fun setObservers() {

		viewModel.images.observe(this@AddAccommodationFragment, Observer {
			imagesAdapter.setData(it)
		})

		viewModel.addingAccommodation.observe(this, Observer {
			when (it) {
				is OnSuccess -> navigation.navigateToAccommodations()
				is OnError -> Log.d(TAG, "initView: OnError")
			}
		})
	}

	override fun initView() {

		actionBarSetup()
		setupRecyclerView()
		setClickListeners()
	}

	private fun setClickListeners() {
		btAddService.setOnClickListener {
			AddServiceDialog.build(context!!) {
				confirmedService = this@AddAccommodationFragment::addService
			}.show()
			focusContainer.clearFocus()

		}

		btAddImage.setOnClickListener {
			openFileChooser()
		}
	}

	private fun setupRecyclerView() {
		rvServices.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@AddAccommodationFragment.servicesAdapter
		}

		rvImages.apply {
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
			adapter = this@AddAccommodationFragment.imagesAdapter
		}
	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		inflater.inflate(R.menu.confirm_action, menu)
		setOnMenuItemClickListener(menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setOnMenuItemClickListener(menu: Menu) =
		menu.findItem(R.id.confirmAction).setOnMenuItemClickListener {
			checkIfInformationIsFilled()
			true
		}

	private fun addAccommodation() =
		viewModel.addAccommodation(createAccommodation())

	private fun createAccommodation() =
		AccommodationEntity(
			id = 0,
			name = etName.asString(),
			type = when (sp_categories.selectedItemPosition) {
				0 -> "SMESTAJ_APARTMAN"
				1 -> "SMESTAJ_BB"
				else -> "SMESTAJ_HOTEL"
			},
			cancellingFee = if (etCancellingFee.asString().isNotEmpty()) etCancellingFee.asString().toFloat() else 0f,
			cancellingDays = if (etCancellingDays.asString().isNotEmpty()) etCancellingDays.asString().toInt() else 0,
			description = etDescription.text.toString(),
			services = ArrayList(servicesAdapter.getData()),
			address = Address(
				id = 0,
				latitude = etLatitude.text.toString().toFloat(),
				longitude = etLongitude.text.toString().toFloat(),
				city = etCity.text.toString(),
				street = etAddress.text.toString(),
				zipCode = etZipCode.text.toString().toInt(),
				num = etNum.text.toString().toInt()
			),
			category = "",
			rating = 0f,
			pictures = ArrayList(imagesAdapter.getData())
		)

	private fun addService(name: String, desc: String, price: Float) {
		with(servicesAdapter) {
			ArrayList<Service>().also {
				it.addAll(getData() + Service(1, name, desc, price))
				setData(it)
			}
		}

	}

	private fun addImage(encodedImage: String) =
		imagesAdapter.getData().toMutableList().apply {
			add(encodedImage)
		}.run { viewModel.images.postValue(this) }

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
			if (requestCode == FILE_CHOOSER_IMAGE && resultCode == Activity.RESULT_OK) {
				activity?.contentResolver?.openInputStream(this).also {
					addImage(BitmapFactory.decodeStream(it).toBase64())
				}
			}
		}
	}

	private fun checkIfInformationIsFilled() {
		when {
			etName.asString().isEmpty() -> showToast("You must enter accommodation name!")
			etDescription.asString().isEmpty() -> showToast("You must enter description!")
			etLatitude.asString().isEmpty() -> showToast("You must enter latitude!")
			etLongitude.asString().isEmpty() -> showToast("You must enter longitude!")
			etCity.asString().isEmpty() -> showToast("You must enter city!")
			etZipCode.asString().isEmpty() -> showToast("You must enter zip code!")
			etAddress.asString().isEmpty() -> showToast("You must enter street name!")
			etNum.asString().isEmpty() -> showToast("You must enter street number!")
			imagesAdapter.getData().isEmpty() -> showToast("You must add at least one image!")
			else -> addAccommodation()
		}
	}

}