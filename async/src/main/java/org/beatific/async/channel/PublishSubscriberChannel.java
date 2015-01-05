package org.beatific.async.channel;

import org.beatific.async.message.Message;

public class PublishSubscriberChannel<T> implements MessageChannel<T>{

	public boolean send(Message<T> message) {
		return false;
	}

	public boolean send(Message<T> message, long timeout) {
		return false;
	}

}
