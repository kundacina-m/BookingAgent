package com.example.bookingagent.domain.crud

import com.example.bookingagent.utils.WrappedResponse
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalCRUD<T> {
	fun add(item: T): Single<WrappedResponse<Long>>
	fun remove(item: T): Single<WrappedResponse<Long>>
	fun get(id: String): Single<WrappedResponse<T>>
	fun getAll(): Flowable<WrappedResponse<ArrayList<T>>>
}