package main.java.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [11-3 處理請求參數的編碼] 此範例示範在GET請求方法下，請求參數編碼的處理
 * 在Tomcat"7-"的環境下，客戶端請求 /processCharsetGet?name=李，才會看到正確的結果
 * @author William
 */
@WebServlet("/processCharsetGet")
public class ProcessCharsetGetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("name");			// ↓Java預設使用UTF-8
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(name);		// ↑客戶端使用ISO-8859-1
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}