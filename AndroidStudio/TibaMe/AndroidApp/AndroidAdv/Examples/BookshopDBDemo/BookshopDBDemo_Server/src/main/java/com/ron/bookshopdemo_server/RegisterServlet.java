package com.ron.bookshopdemo_server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@SuppressWarnings("serial")
@WebServlet("/RegisterServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1 MB
		maxFileSize = 5 * 1024 * 1024, // 5 MB
		maxRequestSize = 10 * 1024 * 1024 // 10 MB
)
public class RegisterServlet extends HttpServlet {
	private final static String CONTENT_TYPE = "application/json; charset=UTF-8";

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		try {
			// 取得帳號
			String uid = request.getParameter("uid");
			// 取得密碼
			String password = request.getParameter("password");
			// 如果帳密任一為null就回傳錯誤
			if (uid == null || password == null) {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				response.getWriter().write("Missing user ID or password.");
				return;
			}
			// 取得圖片
			Part imagePart = request.getPart("image");

			String imageName = null;
			if (imagePart != null) {
				// 取得圖片儲存目錄
				File uploadDir = getUploadDirectory();
				// 取得上傳的圖片名稱
				imageName = uid + "_" + imagePart.getSubmittedFileName();
				File file = new File(uploadDir, imageName);
				// 將圖片存檔
				try (InputStream inputStream = imagePart.getInputStream();
						FileOutputStream outputStream = new FileOutputStream(file)) {
					byte[] buffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, bytesRead);
					}
				}
			}

			// 建立User物件，帳密一定要有，圖片可以為null
			User user = new User(uid, password, imageName);
			UserDao userDao = new UserDao();
			userDao.insert(user);
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("Image uploaded successfully with userId: " + uid);
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Error processing upload: " + e.getMessage());
		}
	}

	/** 建立圖片儲存目錄並回傳 */
	public static File getUploadDirectory() {
		// 取得使用者家目錄
		String userHome = System.getProperty("user.home");
		// 定義圖片儲存目錄
		File uploadDir = new File(userHome, "upload");
		// 如果目錄不存在就建立，已存在就回傳
		if (!uploadDir.exists()) {
			boolean created = uploadDir.mkdirs();
			if (created) {
				System.out.println("Upload directory created at: " + uploadDir.getAbsolutePath());
			} else {
				System.out.println("Failed to create upload directory.");
			}
		} else {
			System.out.println("Upload directory already exists at: " + uploadDir.getAbsolutePath());
		}
		return uploadDir;
	}

	@Override
	public void doGet(HttpServletRequest rq, HttpServletResponse rp) throws ServletException, IOException {
		String outStr = "Server is running!";
		rp.setContentType(CONTENT_TYPE);
		PrintWriter out = rp.getWriter();
		out.println(outStr);
	}

}