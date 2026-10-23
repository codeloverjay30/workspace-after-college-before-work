package com.ron.bookshopdemo_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class BookDao implements Dao<Book> {
	DataSource dataSource;

	public BookDao() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public void insert(Book book) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteBy(Book book) {
		// TODO Auto-generated method stub
	}

	@Override
	public Book findBy(Book book) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAll() {
		List<Book> bookList = new ArrayList<Book>();
		String sql = "select isbn, book_name, price, author from book;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String isbn = rs.getString(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				String author = rs.getString(4);
				Book book = new Book(isbn, name, price, author);
				bookList.add(book);
			}
			return bookList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}

}
