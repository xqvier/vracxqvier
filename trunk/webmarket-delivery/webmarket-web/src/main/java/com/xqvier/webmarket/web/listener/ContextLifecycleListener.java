package com.xqvier.webmarket.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.xqvier.webmarket.web.util.Counter;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextLifecycleListener implements ServletContextListener {
    private ServletContext context = null;

    /**
     * Default constructor. 
     */
    public ContextLifecycleListener() {
        super();
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        context = arg0.getServletContext();
        context.setAttribute("counter", new Counter());
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        arg0.getServletContext().removeAttribute("counter");
    }
	
}
