package com.example.bookingagent.data.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

@Root(name = "soap12:Body", strict = false)
public class EnvelopeBody {
	
	@Element(name = "TodaysDilbert",required = false)
	@Namespace(reference = "http://gcomputer.net/webservices/")
	private TodayFuckingGilbert data;
	
	public TodayFuckingGilbert getGilbert() {
		return data;
	}
	
	public void setGilbert(TodayFuckingGilbert data) {
		this.data = data;
	}
}
