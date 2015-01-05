package org.beatific.async.user;

import javax.servlet.AsyncContext;

public class Address {

	private String id;
	private AsyncContext address;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public AsyncContext getAddress() {
		return address;
	}
	public void setAddress(AsyncContext address) {
		this.address = address;
	}
}
