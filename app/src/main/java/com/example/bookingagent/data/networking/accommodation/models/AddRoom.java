package com.example.bookingagent.data.networking.accommodation.models;

import java.util.List;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class AddRoom {
	
	@Element(name = "num") private int num;
	@Element(name = "floor") private int floor;
	@Element(name = "bedNum") private int bedNum;
	@Element(name = "price") private int price;
	@Element(name = "images") private List<String> images;
	
	public AddRoom(int num, int floor, int bedNum, int price, List<String> images) {
		this.num = num;
		this.floor = floor;
		this.bedNum = bedNum;
		this.price = price;
		this.images = images;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	public int getBedNum() {
		return bedNum;
	}
	
	public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public List<String> getImages() {
		return images;
	}
	
	public void setImages(List<String> images) {
		this.images = images;
	}
}
