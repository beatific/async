package org.beatific.async;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;

public class ChatRoom {

	private static ChatRoom INSTANCE = new ChatRoom();

	public static ChatRoom getInstance() {
		return INSTANCE;
	}

	private List<AsyncContext> clients = new LinkedList<AsyncContext>();
	private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();
	private Thread messageHandlerThread;
	private boolean running;

	private ChatRoom() {
	}

	public synchronized void init() {
		System.out.println("init() : " + messageQueue.size());
		running = true;
		Runnable handler = new Runnable() {
			public void run() {
				while (running) {
					try {
						String message = messageQueue.take();
						sendMessageToAllInternal(message);
					} catch (InterruptedException ex) {
						break;
					}
				}
			}
		};
		messageHandlerThread = new Thread(handler);
		messageHandlerThread.start();
	}

	public synchronized void enter(final AsyncContext asyncCtx) {
		clients.add(asyncCtx);
		System.out.println("length : " + clients.size());
	}

	public synchronized void sendMessageToAll(String message) {
		try {
			messageQueue.put(message);
			System.out.println("message length : " + messageQueue.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendMessageToAllInternal(String message) {
		System.out.println("message" + message);
		System.out.println("clients.size()" + clients.size());
		for (AsyncContext ac : clients) {
			try {
				sendMessageTo(ac, message);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		clients.clear();
	}

	private void sendMessageTo(AsyncContext ac, String message) throws IOException {
		PrintWriter acWriter = ac.getResponse().getWriter();
		System.out.println(toJSAppendCoomand(message));
		acWriter.println(toJSAppendCoomand(message));
		acWriter.flush();
		ac.complete();
	}

	private String toJSAppendCoomand(String message) {
		return "<script type='text/javascript'>\n" + "window.parent.chatapp.append({ message: \"" + message + "\" });\n" + "</script>\n";
	}

	public void close() {
		running = false;
		messageHandlerThread.interrupt();
		for (AsyncContext ac : clients) {
			ac.complete();
		}
	}

}
