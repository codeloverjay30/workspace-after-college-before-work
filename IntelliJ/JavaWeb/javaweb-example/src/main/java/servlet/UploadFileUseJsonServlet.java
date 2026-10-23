package servlet;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bean.Member;

/**
 * [19-2 上傳檔案] 此範例示範讀入前端傳來夾帶檔案的JSON格式字串
 * 檔案會儲存在D:/uploaded_files
 * @author William
 */
@WebServlet("/uploadFileUseJson")
public class UploadFileUseJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Gson GSON = new Gson();
	private static final Decoder DECODER = Base64.getDecoder();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			resp.setContentType("application/json");
			Member member = GSON.fromJson(req.getReader(), Member.class);
			String imgBase64Str = member.getImgBase64Str();
			byte[] bytes = DECODER.decode(imgBase64Str);
			Files.write(Paths.get("D:/uploaded_files/avatar.png"), bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
