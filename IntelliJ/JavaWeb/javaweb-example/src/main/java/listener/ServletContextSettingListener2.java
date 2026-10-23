package listener;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * [21-2 事件與監聽器] 此範例示範SerlvetContextList加上ServletContext初始參數的用法
 * 修改前例的共享資料-檔案根目錄，改用ServletContext初始參數的方式設定
 * 取得所有ServletContext初始參數 → 加入至共享資料
 * @author William
 */
@WebListener
public class ServletContextSettingListener2 implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Enumeration<String> names = context.getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            context.setAttribute(name, context.getInitParameter(name));
        }
    }
}
