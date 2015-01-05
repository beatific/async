package org.beatific.async.user;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class RecipientList {

	private Queue<User> attender;
	private List<User> users;
	private HashMap<User, Address> addresses;
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition notEmpty = lock.newCondition();
	private final AtomicInteger count = new AtomicInteger(0);
	
	public synchronized void join(User user) {
		users.add(user);
	}
	
	public synchronized void attend(User user, Address address) {
		addresses.put(user, address);
		attender.add(user);
	}
	
	public synchronized boolean isReady() {
		Set<User> keys = addresses.keySet();
		return keys.containsAll(users);
	}
	
	
	public synchronized Address poll() {
		User user = attender.poll();
		if(user == null) {
			addresses.clear();
			return null;
		}
		return addresses.get(user);
	}
}
