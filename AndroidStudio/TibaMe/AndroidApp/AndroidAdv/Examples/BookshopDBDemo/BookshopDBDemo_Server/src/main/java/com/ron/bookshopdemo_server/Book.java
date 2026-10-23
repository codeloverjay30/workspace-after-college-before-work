package com.ron.bookshopdemo_server;

public class Book {
	private String isbn;
	private String name;
	private double price;
	private String author;

	public Book(String isbn, String name, double price, String author) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.price = price;
		this.author = author;
	}

	@Override
	public String toString() {
		return "ISBN: " + isbn + ", name: " + name + ", price: " + price;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
