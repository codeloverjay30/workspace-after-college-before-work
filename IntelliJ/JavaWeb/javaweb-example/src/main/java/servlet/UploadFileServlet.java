package servlet;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

/**
 * [19-2 上傳檔案] 此範例示範讀入前端傳來的檔案
 * 檔案會儲存在D:/uploaded_files
 * @author William
 */
@MultipartConfig(location = "D:/uploaded_files")
@WebServlet("/uploadFile")
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Part part = req.getPart("file");
			part.write(getFileName(part));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getFileName(Part part) {
		String fileDesc = part.getHeader("Content-Disposition");
		int index = fileDesc.indexOf("filename=\"");
		String fileName = fileDesc.substring(index + 10, fileDesc.length() - 1);
		return FilenameUtils.getName(fileName);
	}
}
