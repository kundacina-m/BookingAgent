package com.example.bookingagent.screens.messages

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.ReservationEntity
import com.example.bookingagent.data.model.MessageThread
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import kotlinx.android.synthetic.main.fragment_messages.rvMessages
import kotlinx.android.synthetic.main.toolbar_main.*

class MessagesFragment : BaseFragment<MessagesViewModel, MessagesRoutes>() {

	private val adapter by lazy {
		MessagesAdapter().apply {
			onItemClicked = this@MessagesFragment::onItemClicked
		}
	}

	override fun getLayoutId(): Int = R.layout.fragment_messages

	override fun setObservers() {
		viewModel.messageThreads.observe(this, Observer {
			when (it) {
				is OnSuccess -> setDataToAdapter(it.item)
				is OnError -> Log.d(TAG, "setObservers: NEKI ERROR")
			}
		})
	}

	private fun setDataToAdapter(list: List<ReservationEntity>) {
		val listOfThreads = arrayListOf<MessageThread>()
		list.forEach {
			listOfThreads.add(MessageThread(it.id, it.firstname, it.lastname))
		}
		adapter.setData(listOfThreads)
	}

	override fun initView() {
		setupRecyclerView()
		setActionBar(toolbar_top,false)
		viewModel.getAllMessages()

	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		menu.clear()
		super.onCreateOptionsMenu(menu, inflater)
	}

	private fun setupRecyclerView() {
		rvMessages.apply {
			layoutManager = LinearLayoutManager(context)
			this.adapter = this@MessagesFragment.adapter
		}
	}

	private fun onItemClicked(item: MessageThread) =
		navigation.navigateToThread(item.resId)

}