package org.beatific.async.room;

import java.util.ArrayList;
import java.util.List;

import org.beatific.async.user.User;

public class ChattingRoom {
	
	private List<User> recipientList = new ArrayList<User>();;
	
	//enter
	public void enter(User recipient){
		recipientList.add(recipient);
	}
	//leave
	public void leave(User recipient){
		recipientList.remove(recipient);
	}
	//send message
	public void sendMessage() {
		for(User recipient : recipientList) {
			
		}
	}
}
