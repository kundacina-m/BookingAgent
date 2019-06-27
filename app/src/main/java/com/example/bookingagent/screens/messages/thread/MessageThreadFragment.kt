package com.example.bookingagent.screens.messages.thread

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseFragment
import com.example.bookingagent.R
import com.example.bookingagent.data.db.entities.MessageEntity
import com.example.bookingagent.data.model.ScheduleUnit
import com.example.bookingagent.utils.WrappedResponse.OnError
import com.example.bookingagent.utils.WrappedResponse.OnSuccess
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.fragment_message_thread.*
import kotlinx.android.synthetic.main.fragment_message_thread.view.*
import kotlinx.android.synthetic.main.toolbar_main.toolbar_top

class MessageThreadFragment : BaseFragment<MessageThreadViewModel, MessageThreadRoutes>() {

    private val args: MessageThreadFragmentArgs by navArgs()
    private val adapter by lazy {
        MessageThreadAdapter().apply {
            onLongClickListener = this@MessageThreadFragment::deleteMessage
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_message_thread
    override fun setObservers() {
        viewModel.addedMessage.observe(this, Observer {
            adapter.getData().toMutableList().apply {
                add(it)
            }.run { viewModel.messages.postValue(OnSuccess(this)) }
        })

        viewModel.messages.observe(this, Observer {
            when (it) {
                is OnSuccess -> onMessagesRecieved(it.item)
                is OnError -> Log.d(TAG, "setObservers: ERROR KOD THREAD MESASGE")
            }
        })

        viewModel.messageSentStatus.observe(this, Observer {
            when (it) {
                is OnSuccess -> etMessage.setText("")
                is OnError -> Log.d(TAG, "setObservers: ERROR KOD SLANJA PORUKE")
            }
        })
    }

    override fun initView() {
        actionBarSetup()
        viewModel.fetchMessages(args.resId)
        setRecyclerView()

        btSendMessage.setOnClickListener {
            sendMessage(etMessage.asString())
            etMessage.setText("")
        }
    }

    private fun actionBarSetup() {
        setActionBar(toolbar_top, true)
    }

    private fun onMessagesRecieved(list: List<MessageEntity>) {
        adapter.setData(list)
        rvMessageThread.scrollToPosition(adapter.itemCount - 1)
        actionBar?.title = list[0].firstname + " " + list[0].lastname
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setRecyclerView() {
        rvMessageThread.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@MessageThreadFragment.adapter
        }
    }

    private fun sendMessage(message: String) =
        viewModel.sendMessage(args.resId, message)


    private fun deleteMessage(id: Int) {
        viewModel.deleteMessage(id)
    }
}