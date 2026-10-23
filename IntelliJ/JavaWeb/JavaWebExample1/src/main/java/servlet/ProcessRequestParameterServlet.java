package main.java.servlet;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [11-1 處理請求參數] 此範例示範請求參數的處理
 * 此Servlet可以接收processRequestParameter.html<form>送來的請求參數
 *
 * @author William
 */
@WebServlet("/processRequestParameter")
public class ProcessRequestParameterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 使用getParameter()
            String name = req.getParameter("name");
            System.out.println(name);
            String age = req.getParameter("age");
            System.out.println(age);

            // 使用getParameterValues()
            String[] skills = req.getParameterValues("skills");
            for (String skill : skills) {
                System.out.println(skill);
            }

            // 使用getParameterNames()
            Enumeration<String> names = req.getParameterNames();
            while (names.hasMoreElements()) {
                String pName = names.nextElement();
                System.out.println(pName + ": " + req.getParameter(pName));
            }

            // 使用getParameterMap()
            Map<String, String[]> paramMap = req.getParameterMap();
            paramMap.forEach((key, value) -> {
                System.out.println(key + ": " + Arrays.toString(value));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}