package com.ron.bookshopdemo_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// prints input for debug
		// request.getReader().lines().forEach(System.out::print);
		Gson gson = new Gson();
		BookDao bookDao = new BookDao();
		List<Book> bookList = bookDao.getAll();
		writeText(response, gson.toJson(bookList));
	}

	private void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		out.print(outText);
		// 將輸出資料列印出來除錯用
		System.out.println("output: " + outText);
	}
	
	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		String outStr = "Server is running!";
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		out.println(outStr);
	}

}