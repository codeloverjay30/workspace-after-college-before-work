package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Member;

/**
 * [19-3 下載檔案] 此範例示範用JSON格式傳檔案
 * 與downloadFileUseJson.html串接
 * @author William
 */
@WebServlet("/downloadFileUseJson")
public class DownloadFileUseJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new Gson();
	private static final Encoder ENCODER = Base64.getEncoder();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.setContentType("application/json");
			PrintWriter pw = resp.getWriter();
			Member member = new Member();
			member.setUsername("william0117");
			member.setNickname("William");
			String base64Str = ENCODER.encodeToString(getImage());
			member.setImgBase64Str(base64Str);
			pw.write(GSON.toJson(member));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 讀入圖檔
	private byte[] getImage() throws IOException {
		String fileRootPath = System.getProperty("catalina.home") + "/file/";
		return Files.readAllBytes(Paths.get(fileRootPath, "polar-bears.jpg"));
	}
}
