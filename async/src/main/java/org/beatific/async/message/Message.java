package org.beatific.async.message;

import org.beatific.async.user.User;

public class Message<T> {

	private Header header;
	private Payload<T> payload;
	
	public void setSender(User sender){
		header.put("sender", sender);
	}
	
	public void setRecipient(User recipient){
		header.put("recipient", recipient);
	}
	
	public void setRecipientList(User recipient){
		header.put("recipient", recipient);
	}
	
	public Header getHeader() {
		return header;
	}
	public T getPayload() {
		return payload.getPayload();
	}
	public void setPayload(T payload) {
		this.payload.setPayload(payload);;
	}
}
