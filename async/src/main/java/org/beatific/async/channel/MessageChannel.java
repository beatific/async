package org.beatific.async.channel;

import org.beatific.async.message.Message;

public interface MessageChannel<T> {

	public boolean send(Message<T> message);
	public boolean send(Message<T> message, long timeout);
}
