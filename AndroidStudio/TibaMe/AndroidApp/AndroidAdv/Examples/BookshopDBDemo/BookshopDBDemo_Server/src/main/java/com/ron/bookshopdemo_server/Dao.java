package com.ron.bookshopdemo_server;
import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	void insert(T obj) throws SQLException;

	void update(T obj) throws SQLException;

	void deleteBy(T obj) throws SQLException;

	T findBy(T obj) throws SQLException;

	List<T> getAll() throws SQLException;

}