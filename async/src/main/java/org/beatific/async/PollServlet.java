package org.beatific.async;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/poll", asyncSupported = true)
public class PollServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		res.setHeader("Cache-Control", "private");
		res.setHeader("Pragma", "no-cache");
		res.setCharacterEncoding("UTF-8");
		PrintWriter writer = res.getWriter();
		// for IE
		writer.println("<!-- Comet is a programming technique that enables web servers to send data to the client without having any need for the client to request it. -->\n");
		writer.flush();
		AsyncContext asyncCtx = req.startAsync();
		addToChatRoom(asyncCtx);
		return;
	}

	private void addToChatRoom(AsyncContext asyncCtx) {
		asyncCtx.setTimeout(0);
		ChatRoom.getInstance().enter(asyncCtx);
	}

}