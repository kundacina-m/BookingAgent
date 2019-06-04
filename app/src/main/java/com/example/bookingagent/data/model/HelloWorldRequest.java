package com.example.bookingagent.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class HelloWorldRequest {

    @Element(name = "prob:name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
