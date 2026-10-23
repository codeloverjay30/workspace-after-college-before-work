package main.java.servlet;

import java.io.InputStream;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * [21-1 ServletContext] 此範例示範使用ServletContext常用方法
 *
 * @author William
 */
@WebServlet("/useServletContext")
public class UseServletContextServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // 取得ServletContext物件
        ServletContext context = getServletContext();

        // 設定共享資料(識別字串, 屬性物件)
        context.setAttribute("JavaWeb", "so easy");

        // 取得InputStream物件，指向 WebContent/WEB-INF/img/polar-bears.jpg
        InputStream is = context.getResourceAsStream("/WEB-INF/img/polar-bears.jpg");

        // 取得路徑 WebContent/META-INF/ 下的所有資源(檔案)代表字串
        // 會印出 "/META-INF/MANIFEST.MF"、"/META-INF/context.xml"
        Set<String> paths = context.getResourcePaths("/META-INF");
        for (String path : paths) {
            System.out.println(path);
        }

        // 取得應用系統環境根路徑
        // 會印出 "/javaweb-example"
        String contextPath = context.getContextPath();
        System.out.println(contextPath);

        // 取得MIME Type
        String pdf = context.getMimeType(".pdf");    // application/pdf
        String png = context.getMimeType(".png");    // image/png
    }
}
