package com.xqvier.webmarket.web.listener;

import java.util.LinkedList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xqvier.webmarket.common.entity.Product;
import com.xqvier.webmarket.web.bean.UserBean;
import com.xqvier.webmarket.web.pojo.Cart;

/**
 * Application Lifecycle Listener implementation class SessionLifecycleListener
 * 
 */
@WebListener
public class SessionLifecycleListener implements HttpSessionListener {

    /**
     * Default constructor.
     */
    public SessionLifecycleListener() {
    }

    /**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("cart",
                new Cart(new LinkedList<Product>()));
        
        httpSessionEvent.getSession().setAttribute("userBean", new UserBean());
    }

    /**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().removeAttribute("cart");
        httpSessionEvent.getSession().removeAttribute("userBean");
    }

}
