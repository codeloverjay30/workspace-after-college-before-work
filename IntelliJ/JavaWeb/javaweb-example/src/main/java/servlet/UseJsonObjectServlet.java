package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * [13-5 使用JSON]-使用JsonObject
 * 此範例示範利用JsonObject，回應自訂屬性的JSON格式資料至前端
 * @author William
 */
@WebServlet("/useJsonObject")
public class UseJsonObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		PrintWriter pw = resp.getWriter();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("isSuccess", false);
		jsonObject.addProperty("message", "名稱不可為空");
		pw.print(jsonObject.toString());
	}
}
