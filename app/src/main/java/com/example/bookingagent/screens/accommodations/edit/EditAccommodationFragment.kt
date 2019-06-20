package com.example.bookingagent.screens.accommodations.edit

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.model.Address
import com.example.bookingagent.data.model.Service
import com.example.bookingagent.screens.accommodations.DialogAddService
import com.example.bookingagent.screens.accommodations.ServicesAdapter
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_edit_accommodation.btAddService
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etAddress
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etCancellingFee
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etCity
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etDescription
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etLatitude
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etLongitude
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etName
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etNum
import kotlinx.android.synthetic.main.fragment_edit_accommodation.etZipCode
import kotlinx.android.synthetic.main.fragment_edit_accommodation.focusContainer
import kotlinx.android.synthetic.main.fragment_edit_accommodation.rvServices
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class EditAccommodationFragment : BaseFragment<EditAccommodationViewModel, EditAccommodationRoutes>() {

	private val args: EditAccommodationFragmentArgs by navArgs()

	private val adapter by lazy { ServicesAdapter() }

	override fun getLayoutId(): Int = R.layout.fragment_edit_accommodation

	override fun setObservers() {
		viewModel.editStatus.observe(this, Observer {
			when (it) {
				is OnSuccess -> navigation.navigateToAccommodationDetails()
				is OnError -> Log.d(TAG, "setObservers: ERROR")
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
			adapter = this@EditAccommodationFragment.adapter
		}
	}

	private fun setClickListeners() {
		btAddService.setOnClickListener {
			DialogAddService.build(context!!) {
				confirmedService = this@EditAccommodationFragment::addService
			}.show()

			focusContainer.clearFocus()
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
			editAccommodation()
			true
		}
	}

	private fun editAccommodation() =
		viewModel.editAccommodation(changedAccommodation())

	private fun changedAccommodation() =
		Accommodation(
			id = args.id,
			cancellingFee = etCancellingFee.text.toString().toFloat(),
			type = args.type,
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
			services = ArrayList(adapter.getData())
		)

	private fun addService(name: String, desc: String, price: Float) {
		with(adapter) {
			ArrayList<Service>().also {
				it.addAll(getData() + Service(1, name, desc, price))
				setData(it)
			}
		}
	}
}