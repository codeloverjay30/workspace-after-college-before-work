package com.ron.bookshopdemo_server;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import static com.ron.bookshopdemo_server.Common.*;

@SuppressWarnings("serial")
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		rq.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		User user = gson.fromJson(rq.getReader(), User.class);
		// prints input for debug
		System.out.println("dataIn: " + user.toString());

		// 回傳註冊結果
		boolean registered = false;
		if (user.getUid().isBlank() || user.getPassword().isBlank()) {
			registered = false;
		} else {
			validUid = user.getUid();
			validPassword = user.getPassword();
			registered = true;
		}
		
		JsonObject result = new JsonObject();
		result.addProperty("registered", registered);
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		out.println(result.toString());
		// prints output for debug
		System.out.println("dataOut: " + result.toString());
	}

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		String outStr = "Server is running!";
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		out.println(outStr);
	}

}