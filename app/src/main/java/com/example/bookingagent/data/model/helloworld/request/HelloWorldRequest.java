package com.example.bookingagent.data.model.helloworld.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;



@Root
@Namespace(prefix = "prob", reference = "http://proba.com")
public class HelloWorldRequest {

    @Element(name = "name")
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
