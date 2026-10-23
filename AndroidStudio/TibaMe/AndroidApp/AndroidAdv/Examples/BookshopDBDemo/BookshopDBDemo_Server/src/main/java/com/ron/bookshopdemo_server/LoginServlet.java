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

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			Gson gson = new Gson();
			User user = gson.fromJson(request.getReader(), User.class);
			// prints input for debug
			System.out.println("dataIn: " + user.toString());

			// 回傳登入結果
			boolean logged = false;
			UserDao userDao = new UserDao();
			User userReturn = userDao.findBy(user);
			if (userReturn != null) {
				logged = true;
			}
			JsonObject result = new JsonObject();
			result.addProperty("logged", logged);
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(CONTENT_TYPE);
			response.getWriter().write(result.toString());
			System.out.println("dataOut: " + result.toString());
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Error processing upload: " + e.getMessage());
		}
	}

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		String outStr = "Server is running!";
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		out.println(outStr);
	}

}