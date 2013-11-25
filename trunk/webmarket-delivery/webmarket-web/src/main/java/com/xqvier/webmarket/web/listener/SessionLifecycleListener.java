package com.xqvier.webmarket.web.listener;

import java.util.LinkedList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.xqvier.webmarket.business.entity.Product;
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
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("panier",
                new Cart(new LinkedList<Product>()));
    }

    /**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().removeAttribute("panier");
    }

}
