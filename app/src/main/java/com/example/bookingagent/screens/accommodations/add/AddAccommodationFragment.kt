package com.example.bookingagent.screens.accommodations.add

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.Accommodation
import com.example.bookingagent.data.model.Address
import com.example.bookingagent.data.model.Service
import com.example.bookingagent.screens.accommodations.DialogAddService
import com.example.bookingagent.screens.accommodations.ServicesAdapter
import com.example.bookingagent.utils.RequestError
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_add_accommodation.btAddService
import kotlinx.android.synthetic.main.fragment_add_accommodation.etAddress
import kotlinx.android.synthetic.main.fragment_add_accommodation.etCancellingFee
import kotlinx.android.synthetic.main.fragment_add_accommodation.etCity
import kotlinx.android.synthetic.main.fragment_add_accommodation.etDescription
import kotlinx.android.synthetic.main.fragment_add_accommodation.etLatitude
import kotlinx.android.synthetic.main.fragment_add_accommodation.etLongitude
import kotlinx.android.synthetic.main.fragment_add_accommodation.etName
import kotlinx.android.synthetic.main.fragment_add_accommodation.etNum
import kotlinx.android.synthetic.main.fragment_add_accommodation.etZipCode
import kotlinx.android.synthetic.main.fragment_add_accommodation.focusContainer
import kotlinx.android.synthetic.main.fragment_add_accommodation.rvServices
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class AddAccommodationFragment : BaseFragment<AddAccommodationViewModel, AddAccommodationRoutes>() {

	private val adapter by lazy { ServicesAdapter() }

	override fun getLayoutId(): Int = R.layout.fragment_add_accommodation

	override fun setObservers() {

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

	private fun setClickListeners() =
		btAddService.setOnClickListener {
			DialogAddService.build(context!!) {
				confirmedService = this@AddAccommodationFragment::addService
			}.show()
			focusContainer.clearFocus()

		}

	private fun setupRecyclerView() =
		rvServices.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = this@AddAccommodationFragment.adapter
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
			addAccommodation()
			true
		}

	private fun addAccommodation() =
		viewModel.addAccommodation(createAccommodation())

	private fun createAccommodation() =
		Accommodation(
			id = 0,
			name = etName.text.toString(),
			type = "SMESTAJ_HOTEL",
			cancellingFee = etCancellingFee.text.toString().toFloat(),
			description = etDescription.text.toString(),
			services = ArrayList(adapter.getData()),
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
			pictures = arrayListOf()
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