package com.example.bookingagent.screens.accommodations.details.rooms.list


import androidx.navigation.fragment.navArgs
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.screens.accommodations.details.AccommodationDetailsFragmentArgs

class RoomsFragment : BaseFragment<RoomsViewModel, RoomsRoutes>() {

    private val args: RoomsFragmentArgs by navArgs()

    override fun getLayoutId(): Int = R.layout.fragment_rooms

    override fun setObservers() {}

    override fun initView() {}


}
