package com.example.bookingagent.data.networking.common;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "address")
public class AddressRequest {

    @Element(name = "latitude") private int latitude;
    @Element(name = "longitude") private int longitude;
    @Element(name = "city") private String city;
    @Element(name = "zipCode") private int zipCode;
    @Element(name = "street") private String street;
    @Element(name = "num") private int num;

    public AddressRequest(int latitude, int longitude, String city, int zipCode, String street, int num) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.num = num;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
