package org.beatific.async.user;

import javax.servlet.AsyncContext;

public class Recipient {
	private String id;
	private String name;
	private AsyncContext address;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AsyncContext getAddress() {
		return address;
	}
	public void setAddress(AsyncContext address) {
		this.address = address;
	}
}
