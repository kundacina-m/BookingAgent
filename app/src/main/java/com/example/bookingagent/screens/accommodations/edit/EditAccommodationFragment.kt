package com.example.bookingagent.screens.accommodations.edit

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
import com.example.bookingagent.data.db.entities.AccommodationEntity
import com.example.bookingagent.data.model.Address
import com.example.bookingagent.data.model.Service
import com.example.bookingagent.screens.accommodations.AddServiceDialog
import com.example.bookingagent.screens.accommodations.ServicesAdapter
import com.example.bookingagent.screens.rooms.ImagesAdapter
import com.example.bookingagent.utils.*
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_add_accommodation.sp_categories
import kotlinx.android.synthetic.main.fragment_edit_accommodation.btAddImage
import kotlinx.android.synthetic.main.fragment_edit_accommodation.btAddService
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etAddress
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etCancellingDays
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etCancellingFee
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etCity
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etDescription
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etLatitude
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etLongitude
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etName
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etNum
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etZipCode
import kotlinx.android.synthetic.main.fragment_edit_accommodation.focusContainer
import kotlinx.android.synthetic.main.fragment_edit_accommodation.rvImages
import kotlinx.android.synthetic.main.fragment_edit_accommodation.rvServices
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class EditAccommodationFragment : BaseFragment<EditAccommodationViewModel, EditAccommodationRoutes>() {

	private val args: EditAccommodationFragmentArgs by navArgs()

	private val servicesAdapter by lazy { ServicesAdapter() }
	private val imagesAdapter by lazy { ImagesAdapter() }

	override fun getLayoutId(): Int = R.layout.fragment_edit_accommodation

	override fun setObservers() {

		viewModel.images.observe(this@EditAccommodationFragment, Observer {
			imagesAdapter.setData(it)
		})

		viewModel.editStatus.observe(this, Observer {
			when (it) {
				is OnSuccess -> navigation.navigateToAccommodationDetails()
				is OnError -> handleError(it.error)
			}
		})

	}

	override fun initView() {
		actionBarSetup()
		setRecyclerView()
		fillViewWithData()
		setClickListeners()
	}

	private fun setRecyclerView() {
		rvServices.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@EditAccommodationFragment.servicesAdapter
		}

		rvImages.apply {
			layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
			adapter = this@EditAccommodationFragment.imagesAdapter
		}
	}

	private fun setClickListeners() {
		btAddService.setOnClickListener {
			AddServiceDialog.build(context!!) {
				confirmedService = this@EditAccommodationFragment::addService
			}.show()

			focusContainer.clearFocus()
		}

		btAddImage.setOnClickListener {
			openFileChooser()
		}
	}

	private fun fillViewWithData() {
		etName.setText(args.name)
		etDescription.setText(args.description)
		etLatitude.setText(args.address.latitude.toString())
		etLongitude.setText(args.address.longitude.toString())
		etCity.setText(args.address.city)
		etZipCode.setText(args.address.zipCode.toString())
		etAddress.setText(args.address.street)
		etNum.setText(args.address.num.toString())
		etCancellingFee.setText(args.cancellingFee.toString())
		etCancellingDays.setText(args.cancellingDays.toString())

	}

	private fun actionBarSetup() {
		setActionBar(toolbar_top, true)
		actionBar?.title = args.name
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		inflater.inflate(R.menu.confirm_action, menu)
		setOnMenuItemClickListener(menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setOnMenuItemClickListener(menu: Menu) {
		menu.findItem(R.id.confirmAction).setOnMenuItemClickListener {
			checkIfInformationIsFilled()
			true
		}
	}

	private fun editAccommodation() =
		viewModel.editAccommodation(changedAccommodation())

	private fun changedAccommodation() =
		AccommodationEntity(
			id = args.id,
			cancellingFee = etCancellingFee.text.toString().toFloat(),
			cancellingDays = etCancellingDays.text.toString().toInt(),
			type = when (sp_categories.selectedItemPosition) {
				0 -> "SMESTAJ_APARTMAN"
				1 -> "SMESTAJ_BB"
				else -> "SMESTAJ_HOTEL"
			},
			address = Address(
				latitude = etLatitude.text.toString().toFloat(),
				longitude = etLongitude.text.toString().toFloat(),
				city = etCity.text.toString(),
				street = etAddress.text.toString(),
				zipCode = etZipCode.text.toString().toInt(),
				num = etNum.text.toString().toInt()
			),
			description = etDescription.text.toString(),
			name = etName.text.toString(),
			services = ArrayList(servicesAdapter.getData()),
			pictures = imagesAdapter.getData() as ArrayList<String>
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
			if (requestCode == FILE_CHOOSER_IMAGE && resultCode == Activity.RESULT_OK)
				activity?.contentResolver?.openInputStream(this).also {
					val image = BitmapFactory.decodeStream(it).toBase64()
					addImage(image.compressBase64())				}
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
			else -> editAccommodation()
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