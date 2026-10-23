package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * [9-3 @WebServlet] 此範例示範@WebServlet的用法
 * 
 * 在Tomcat啟動時，會呼叫init()，並在Console印出..
 * 	name: William
 * 	age: 30
 * 
 * @author William
 */
@WebServlet(
	urlPatterns = { 			// ←設定映射的網址
		"/webServletDemo",
		"/WEB_SERVLET_DEMO"
	},
	name = "TestServlet", 		// ←設定Servlet的名稱
	loadOnStartup = 1, 			// ←設定啟動順序
	initParams = { 				// ←設定初始化參數(name、age)
		@WebInitParam(name = "name", value = "William"),
		@WebInitParam(name = "age", value = "30")
	}
)
public class WebServletDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override 						// ↓ config內會有name跟age的值
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("name: " + config.getInitParameter("name"));
		System.out.println("age: " + config.getInitParameter("age"));
	}
}
