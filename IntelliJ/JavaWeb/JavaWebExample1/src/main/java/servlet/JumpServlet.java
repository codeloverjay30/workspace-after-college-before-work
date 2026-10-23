package main.java.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * [18-範例 資料共享] 此範例示範使用 作用域物件(Scope Object) 跟 轉發
 * 會轉發至result.jsp
 * @author William
 */
@WebServlet("/jump")
public class JumpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 使用Session作用域物件
            HttpSession session = req.getSession();
            // 加入一個共享資料: 識別字串 = "nickname"，屬性物件 = "William"
            session.setAttribute("nickname", "William");

            // 使用重新導向的方式轉發，導向result.jsp
            resp.sendRedirect("result.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}