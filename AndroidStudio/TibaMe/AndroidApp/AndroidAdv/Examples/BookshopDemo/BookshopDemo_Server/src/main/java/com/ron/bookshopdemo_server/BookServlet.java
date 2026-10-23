package com.ron.bookshopdemo_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@SuppressWarnings("serial")
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static List<Book> books;

    // Static initializer block
    static {
        books = new ArrayList<>();
        books.add(new Book("978-3-16-148410-0", "Book One", 19.99));
        books.add(new Book("978-1-40-289462-3", "Book Two", 25.50));
        books.add(new Book("978-0-14-044913-6", "Book Three", 15.75));
        books.add(new Book("978-0-316-76973-9", "Book Four", 22.00));
        books.add(new Book("978-0-7432-7359-5", "Book Five", 30.45));
        books.add(new Book("978-1-56619-909-4", "Book Six", 10.99));
        books.add(new Book("978-0-307-28044-2", "Book Seven", 18.25));
        books.add(new Book("978-0-395-19395-8", "Book Eight", 17.50));
        books.add(new Book("978-0-06-112008-4", "Book Nine", 24.30));
        books.add(new Book("978-0-7432-7350-2", "Book Ten", 21.75));
    }
	
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";

	@Override
	public void doPost(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		rq.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		// prints input for debug
		// rq.getReader().lines().forEach(System.out::print);
		
		JsonObject result = new JsonObject();
		result.addProperty("books", gson.toJson(books));
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