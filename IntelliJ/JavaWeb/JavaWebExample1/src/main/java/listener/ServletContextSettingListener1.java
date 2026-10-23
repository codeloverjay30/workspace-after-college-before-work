package main.java.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * [21-2 事件與監聽器] 此範例示範SerlvetContextListener用法
 * 在ServletContext物件初始化時，加入一個共享資料-檔案根目錄
 * 所有Servlet都可以透過 context.getAttribute("fileRootPath") 取得檔案根目錄
 * @author William
 */
@WebListener
public class ServletContextSettingListener1 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        context.setAttribute("fileRootPath", "D:/file");
    }
}