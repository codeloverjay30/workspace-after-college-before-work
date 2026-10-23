package com.ron.bookshopdemo_server;

public class User {
	private String uid;
	private String password;
	private String imageName;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String uid, String password, String imageName) {
		super();
		this.uid = uid;
		this.password = password;
		this.imageName = imageName;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", password=" + password + ", imageName=" + imageName + "]";
	}

}
