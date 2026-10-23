package main.java.servlet;

import java.util.Enumeration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [11-2 處理請求標頭] 此範例示範請求標頭的處理
 * 此Servlet示範用3種方式印出請求標頭
 *
 * @author William
 */
@WebServlet("/processRequestHeader")
public class ProcessRequestHeaderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            // 使用getHeader()
            String host = req.getHeader("host");
            System.out.println("host: " + host);

            // 使用getHeaders()
            Enumeration<String> accept = req.getHeaders("accept");
            while (accept.hasMoreElements()) {
                String value = accept.nextElement();
                System.out.println("accept: " + value);
            }

            // 使用getHeaderNames()
            Enumeration<String> headerNames = req.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                System.out.println(name + ": " + req.getHeader(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}