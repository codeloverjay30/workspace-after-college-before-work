package com.ron.bookshopdemo_server;

public class Book {
	private String isbn;
	private String name;
	private double price;

	public Book(String isbn, String name, double price) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.price = price;
	}

	public Book() {
		// TODO Auto-generated constructor stub
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

}
