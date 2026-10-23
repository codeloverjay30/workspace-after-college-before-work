package servlet;

import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [13-範例 回應位元組資料] 此範例示範回應位元組資料至前端的方式
 * 回應一個JPG圖片
 * @author William
 */
@WebServlet("/processByteResponse")
public class ProcessByteResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		String file = "/WEB-INF/img/polar-bears.jpg";
		try (InputStream is = getServletContext().getResourceAsStream(file)) {
			resp.setContentType("image/jpeg"); // ←一定要在處理訊息本體前設定!!
			ServletOutputStream sos = resp.getOutputStream();
			byte[] buf = new byte[is.available()];
			while (is.read(buf) != -1) {
				sos.write(buf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
