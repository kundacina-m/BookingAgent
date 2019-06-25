package com.example.bookingagent.screens.rooms.list

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.RoomEntity
import kotlinx.android.synthetic.main.item_room.view.tvBedsNum
import kotlinx.android.synthetic.main.item_room.view.tvFloorNum
import kotlinx.android.synthetic.main.item_room.view.tvPrice
import kotlinx.android.synthetic.main.item_room.view.tvRoomNum

class RoomsViewHolder(itemView: View) : BaseViewHolder<RoomEntity>(itemView) {

	override fun bind(dataItem: RoomEntity) {
		itemView.tvFloorNum.text = dataItem.floor.toString()
		itemView.tvBedsNum.text = dataItem.bedNums.toString()
		itemView.tvPrice.text = dataItem.price.toString()
		itemView.tvRoomNum.text = dataItem.roomNum.toString()

	}
}