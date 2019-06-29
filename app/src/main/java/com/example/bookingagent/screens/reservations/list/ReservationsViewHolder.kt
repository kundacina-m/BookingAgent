package com.example.bookingagent.screens.reservations.list

import android.view.View
import base.BaseViewHolder
import com.example.bookingagent.data.db.entities.ReservationEntity
import com.example.bookingagent.utils.asString
import kotlinx.android.synthetic.main.item_reservation.view.btUsed
import kotlinx.android.synthetic.main.item_reservation.view.tvAccommodationName
import kotlinx.android.synthetic.main.item_reservation.view.tvBedNum
import kotlinx.android.synthetic.main.item_reservation.view.tvFloorNum
import kotlinx.android.synthetic.main.item_reservation.view.tvFrom
import kotlinx.android.synthetic.main.item_reservation.view.tvOnName
import kotlinx.android.synthetic.main.item_reservation.view.tvPrice
import kotlinx.android.synthetic.main.item_reservation.view.tvRoomNum
import kotlinx.android.synthetic.main.item_reservation.view.tvTo

class ReservationsViewHolder(itemView: View) : BaseViewHolder<ReservationEntity>(itemView) {
	override fun bind(dataItem: ReservationEntity) {
		with(itemView) {
			tvAccommodationName.text = dataItem.accommodationName
			tvRoomNum.text = dataItem.room?.roomNum.toString()
			tvFloorNum.text = dataItem.room?.floor.toString()
			tvBedNum.text = dataItem.room?.bedNums.toString()
			tvOnName.text = (dataItem.firstname + " " + dataItem.lastname)
			tvFrom.text = dataItem.from
			tvTo.text = dataItem.to
			tvPrice.text = dataItem.price.asString()
			btUsed.isEnabled = !dataItem.reservationUsed
		}

	}
}