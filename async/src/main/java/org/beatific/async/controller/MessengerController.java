package org.beatific.async.controller;

import org.beatific.async.ChatRoom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessengerController {

	@RequestMapping(method = RequestMethod.POST, value = "/send")
	public void send() {
		ChatRoom chatRoom = ChatRoom.getInstance();
		chatRoom.sendMessageToAll("message");
	}
	
	//join messenger
	//login messenger
	//enter chatting room(double click other user)
	//invite other user
	//send message
}
