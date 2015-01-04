package org.beatific.async.job;

import org.beatific.async.ChatRoom;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PollingJob {

	@Scheduled(cron="*/1 * * * * ?")
	public void poll() {
		ChatRoom.getInstance().init();
	}
}
