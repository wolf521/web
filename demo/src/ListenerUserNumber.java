/**
 * Created by Administrator on 2017/12/11.
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class ListenerUserNumber implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener, ServletRequestListener {

    private int userNumber = 0;

    // Public constructor is required by servlet spec
    public ListenerUserNumber() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext创建");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext销毁");
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        userNumber++;
        se.getSession().setAttribute("userNumber", userNumber);
        System.out.println(se.getSession().getCreationTime() + "Session创建");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        userNumber--;
        se.getSession().setAttribute("userNumber", userNumber);
        System.out.println(se.getSession().getCreationTime() + "Session销毁");
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
        System.out.println(sbe.getName() + "attribute创建");
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        System.out.println(sbe.getName() + "attribute销毁");
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        System.out.println(sbe.getName() + "attribute替换");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println(servletRequestEvent.getServletRequest().getRemoteAddr() + "request创建");
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        System.out.println(servletRequestEvent.getServletRequest().getRemoteAddr() + "request销毁");
    }
}
