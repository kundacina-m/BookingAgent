package com.example.bookingagent.screens.rooms.list

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.RoomEntity
import com.example.bookingagent.screens.rooms.ImagesAdapter
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.item_room.view.*

class RoomsViewHolder(itemView: View) : BaseViewHolder<RoomEntity>(itemView) {

    override fun bind(dataItem: RoomEntity) {
        itemView.tvFloorNum.text = dataItem.floor.toString()
        itemView.tvBedsNum.text = dataItem.bedNums.toString()
        itemView.tvPrice.text = dataItem.price.asString()
        itemView.tvRoomNum.text = dataItem.roomNum.toString()

        val adapterImages = ImagesAdapter()

        itemView.rvImages.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterImages
        }

        adapterImages.setData(dataItem.images.toList())
    }
}
