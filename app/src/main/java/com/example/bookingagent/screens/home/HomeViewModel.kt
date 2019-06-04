package com.example.bookingagent.screens.home

import base.BaseViewModel
import com.example.bookingagent.data.repository.UserRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(val repository: UserRepository) : BaseViewModel() {
}