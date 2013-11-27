package com.xqvier.webmarket.web.listener;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.xqvier.webmarket.common.service.ProductServiceLocal;
import com.xqvier.webmarket.web.bean.ProductBean;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextLifecycleListener implements ServletContextListener {

    @EJB
    private ProductServiceLocal productServiceLocal;
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
        ProductBean productBean = new ProductBean();
        arg0.getServletContext().setAttribute("productBean", productBean);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        arg0.getServletContext().removeAttribute("productBean");
    }
	
}
