package com.example.bookingagent.data.db.utils;

import androidx.room.TypeConverter;
import com.example.bookingagent.data.db.entities.MessageEntity;
import com.example.bookingagent.data.db.entities.RoomEntity;
import com.example.bookingagent.data.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
	
	@TypeConverter
	public static String fromServiceArrayList(ArrayList<Service> services) {
		Type type = new TypeToken<ArrayList<Service>>() {
		}.getType();
		return new Gson().toJson(services, type);
	}

	@TypeConverter
	public static String fromMessageArrayList(ArrayList<MessageEntity> messages) {
		Type type = new TypeToken<ArrayList<MessageEntity>>() {
		}.getType();
		return new Gson().toJson(messages, type);
	}

	@TypeConverter
	public static String fromScheduleArrayList(ArrayList<ScheduleUnit> schedules) {
		Type type = new TypeToken<ArrayList<ScheduleUnit>>() {
		}.getType();
		return new Gson().toJson(schedules, type);
	}
	
	@TypeConverter
	public static String fromStringArrayList(ArrayList<String> list) {
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	@TypeConverter
	public static String fromRoomArrayList(ArrayList<RoomEntity> roomEntities) {
		Gson gson = new Gson();
		return gson.toJson(roomEntities);
	}
	
	@TypeConverter
	public static String fromOccupiedTimeArrayList(ArrayList<OccupiedTime> occupiedTime) {
		Gson gson = new Gson();
		return gson.toJson(occupiedTime);
	}
	
	
	@TypeConverter
	public static String fromAddress(Address address) {
		Gson gson = new Gson();
		return gson.toJson(address);
	}
	
	@TypeConverter
	public static String fromRoom(RoomEntity room) {
		Gson gson = new Gson();
		return gson.toJson(room);
	}
	
	@TypeConverter
	public static ArrayList<Service> toServiceArrayList(String services) {
		Type type = new TypeToken<ArrayList<Service>>() {
		}.getType();
		return new Gson().fromJson(services, type);
	}

	@TypeConverter
	public static ArrayList<MessageEntity> toMessageArrayList(String messages) {
		Type type = new TypeToken<ArrayList<MessageEntity>>() {
		}.getType();
		return new Gson().fromJson(messages, type);
	}
	
	@TypeConverter
	public static ArrayList<ScheduleUnit> toScheduleArrayList(String schedules) {
		Type type = new TypeToken<ArrayList<ScheduleUnit>>() {
		}.getType();
		return new Gson().fromJson(schedules, type);
	}
	
	@TypeConverter
	public static ArrayList<String> toStringArrayList(String value) {
		Type listType = new TypeToken<ArrayList<String>>() {
		}.getType();
		return new Gson().fromJson(value, listType);
	}
	
	@TypeConverter
	public static ArrayList<RoomEntity> toRoomArrayList(String value) {
		Type listType = new TypeToken<ArrayList<RoomEntity>>() {
		}.getType();
		return new Gson().fromJson(value, listType);
	}
	
	@TypeConverter
	public static ArrayList<OccupiedTime> toOccupiedTimeArrayList(String value) {
		Type listType = new TypeToken<ArrayList<OccupiedTime>>() {
		}.getType();
		return new Gson().fromJson(value, listType);
	}
	
	@TypeConverter
	public static Address toAddress(String value) {
		Type address = new TypeToken<Address>() {
		}.getType();
		return new Gson().fromJson(value, address);
	}
	
	@TypeConverter
	public static RoomEntity toRoomEntity(String value) {
		Type room = new TypeToken<RoomEntity>() {
		}.getType();
		return new Gson().fromJson(value, room);
	}
}
