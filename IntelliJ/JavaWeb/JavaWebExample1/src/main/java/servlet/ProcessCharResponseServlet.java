package main.java.servlet;


import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [13-範例 回應純文字資料] 此範例示範回應純文字資料至前端的方式
 * 客戶端會傳來2個請求參數(name、skills)，回應一個HTML網頁，內含2個請求參數的值
 * @author William
 */
@WebServlet("/processCharResponse")
public class ProcessCharResponseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8"); // ↓一定要在處理訊息本體前設定!!
            resp.setContentType("text/html; charset=UTF-8");
            String name = req.getParameter("name");
            String[] skills = req.getParameterValues("skills");
            PrintWriter pw = resp.getWriter();
            pw.append("<h1>name: " + name + "</h1><br>")
                    .append("<h1>skill: " + Arrays.toString(skills) + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}