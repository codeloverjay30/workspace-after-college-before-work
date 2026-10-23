package web.member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import databaseinfo.DatabaseConnectionInfo;
import web.member.vo.Member;

public class MemberDaoImpl implements MemberDao {

	private DataSource dataSource;

	public MemberDaoImpl(DataSource dataSource) throws NamingException {
		this.dataSource = dataSource;
		if (this.dataSource == null) {
			this.dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/example");
		}
	}

	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}

	@Override
	public int insert(Member member) {
		String sqlCommand = "INSERT INTO MEMBER(username,password,nickname) VALUES (?,?,?);";
		try (
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
			) {

			String username = member.getUsername();
			String password = member.getPassword();
			String nickname = member.getNickname();

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, nickname);

			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Member selectByUserName(String username) {
		String sqlCommand = "SELECT * FROM MEMBER WHERE username = ? ;";

		try (
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){

			preparedStatement.setString(1, username);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String username1 = resultSet.getString("username");
				String password = resultSet.getString("password");
				String nickname = resultSet.getString("nickname");
				Boolean pass = resultSet.getBoolean("pass");
				String creator = resultSet.getString("creator");
				Timestamp createdDate = resultSet.getTimestamp("created_date");
				String updater = resultSet.getString("updater");
				Timestamp lastUpdateDate = resultSet.getTimestamp("LAST_UPDATED_DATE");

				Member member = new Member();

				member.setUsername(username1);
				member.setUsername(password);
				member.setNickname(nickname);
				member.setPass(pass);
				member.setCreator(creator);
				member.setCreatedDate(createdDate);
				member.setUpdator(updater);
				member.setLastUpdateDate(lastUpdateDate);

				return member;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Member selectByUserNameAndPassword(Member member) {
		String sqlCommand = "SELECT * FROM MEMBER WHERE username = ? AND password = ? ;";

		try (
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){

			String username = member.getUsername();
			String password = member.getPassword();
			
			preparedStatement.setString(1,username);
			preparedStatement.setString(2,password);


			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String username1 = resultSet.getString("username");
				String password1 = resultSet.getString("password");
				String nickname = resultSet.getString("nickname");
				Boolean pass = resultSet.getBoolean("pass");
				String creator = resultSet.getString("creator");
				Timestamp createdDate = resultSet.getTimestamp("created_date");
				String updater = resultSet.getString("updater");
				Timestamp lastUpdateDate = resultSet.getTimestamp("LAST_UPDATED_DATE");

				Member newMember = new Member();

				newMember.setUsername(username1);
				newMember.setUsername(password1);
				newMember.setNickname(nickname);
				newMember.setPass(pass);
				newMember.setCreator(creator);
				newMember.setCreatedDate(createdDate);
				newMember.setUpdator(updater);
				newMember.setLastUpdateDate(lastUpdateDate);

				return newMember;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updatePassword(String username, String password) {
		String sqlCommand = "UPDATE MEMBER SET password = ? WHERE username = ? ;";

		try (
				Connection connection = this.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
		){
			preparedStatement.setString(1,password);
			preparedStatement.setString(2,username);
			
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
