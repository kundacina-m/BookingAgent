package com.example.bookingagent.screens.messages

import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import kotlinx.android.synthetic.main.fragment_messages.rvMessages

class MessagesFragment : BaseFragment<MessagesViewModel, MessagesRoutes>() {

	private val adapter by lazy {
		MessagesAdapter()
	}

	override fun getLayoutId(): Int = R.layout.fragment_messages

	override fun setObservers() {}

	override fun initView() {

		setupRecyclerView()

	}

	private fun setupRecyclerView() {
		rvMessages.apply {
			layoutManager = LinearLayoutManager(context)
			this.adapter = this@MessagesFragment.adapter
		}
	}

}