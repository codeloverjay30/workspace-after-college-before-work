package com.ron.bookshopdemo_server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public class UserDao implements Dao<User> {
	DataSource dataSource;

	public UserDao() {
		dataSource = ServiceLocator.getInstance().getDataSource();
	}

	@Override
	public void insert(User user) throws SQLException {
		String sql = "insert into user(uid, password, image_name) values(?, ?, ?)";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user.getUid());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getImageName());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteBy(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public User findBy(User user) throws SQLException {
		User userReturn = null;
		String sql = "select uid from user where uid = ? and password = ?;";
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {
			ps.setString(1, user.getUid());
			ps.setString(2, user.getPassword());
			ResultSet resultSet = ps.executeQuery();
			// 如果輸入帳密正確，只回傳user ID
			if (resultSet.next()) {
				userReturn = new User();
				userReturn.setUid(user.getUid());
			}
			return userReturn;
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
