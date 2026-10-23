package main.java.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [11-3 處理請求參數的編碼] 此範例示範在POST請求方法下，請求參數編碼的處理
 * 若客戶端無設定請求標頭Content-Type編碼之值，就使用UTF-8
 * @author William
 */
@WebServlet("/processCharsetPost")
public class ProcessCharsetPostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String contentCharset = req.getCharacterEncoding();
            if (contentCharset == null) {
                req.setCharacterEncoding("UTF-8");
            }

            String name = req.getParameter("name");
            System.out.println(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
