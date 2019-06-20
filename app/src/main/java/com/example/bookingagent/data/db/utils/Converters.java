package com.example.bookingagent.data.db.utils;

import androidx.room.TypeConverter;
import com.example.bookingagent.data.model.Address;
import com.example.bookingagent.data.db.entities.Room;
import com.example.bookingagent.data.model.ScheduleUnit;
import com.example.bookingagent.data.model.Service;
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
	public static String fromScheduleArrayList(ArrayList<ScheduleUnit> schedules) {
		Type type = new TypeToken<ArrayList<ScheduleUnit>>() {
		}.getType();
		return new Gson().toJson(schedules, type);
	}
	
	//    @TypeConverter
	//    public static String fromCommentArrayList(ArrayList<Comment> comments) {
	//        Type type = new TypeToken<ArrayList<Comment>>() {
	//        }.getType();
	//        return new Gson().toJson(comments, type);
	//    }
	
	@TypeConverter
	public static String fromStringArrayList(ArrayList<String> list) {
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	@TypeConverter
	public static String fromRoomArrayList(ArrayList<Room> rooms) {
		Gson gson = new Gson();
		return gson.toJson(rooms);
	}
	
	@TypeConverter
	public static String fromAddress(Address address) {
		Gson gson = new Gson();
		return gson.toJson(address);
	}
	
	@TypeConverter
	public static ArrayList<Service> toServiceArrayList(String services) {
		Type type = new TypeToken<ArrayList<Service>>() {
		}.getType();
		return new Gson().fromJson(services, type);
	}
	
	@TypeConverter
	public static ArrayList<ScheduleUnit> toScheduleArrayList(String schedules) {
		Type type = new TypeToken<ArrayList<ScheduleUnit>>() {
		}.getType();
		return new Gson().fromJson(schedules, type);
	}
	
	//    @TypeConverter
	//    public static ArrayList<Comment> toCommentArrayList(String comments) {
	//        Type type = new TypeToken<ArrayList<Comment>>() {
	//        }.getType();
	//        return new Gson().fromJson(comments, type);
	//    }
	
	@TypeConverter
	public static ArrayList<String> toStringArrayList(String value) {
		Type listType = new TypeToken<ArrayList<String>>() {
		}.getType();
		return new Gson().fromJson(value, listType);
	}
	
	@TypeConverter
	public static ArrayList<Room> toRoomArrayList(String value) {
		Type listType = new TypeToken<ArrayList<Room>>() {
		}.getType();
		return new Gson().fromJson(value, listType);
	}
	
	@TypeConverter
	public static Address toAddress(String value) {
		Type address = new TypeToken<Address>() {
		}.getType();
		return new Gson().fromJson(value, address);
	}
}
