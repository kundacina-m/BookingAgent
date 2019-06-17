package com.example.bookingagent.screens.rooms.list

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.Room
import kotlinx.android.synthetic.main.item_room.view.tvBedNum
import kotlinx.android.synthetic.main.item_room.view.tvID

class RoomsViewHolder(itemView: View) : BaseViewHolder<Room>(itemView) {

	override fun bind(dataItem: Room) {
		itemView.tvID.text = dataItem.id.toString()
		itemView.tvBedNum.text = dataItem.bedNums.toString()

	}
}