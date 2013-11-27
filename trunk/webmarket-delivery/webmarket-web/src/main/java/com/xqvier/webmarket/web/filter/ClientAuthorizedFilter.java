package com.xqvier.webmarket.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqvier.webmarket.web.bean.UserBean;

/**
 * Servlet Filter implementation class ClientAuthorizedFilter
 */
@WebFilter("/Commande")
public class ClientAuthorizedFilter implements Filter {

    private String contextPath;

    /**
     * Default constructor.
     */
    public ClientAuthorizedFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        contextPath = null;
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (!((UserBean) req.getSession().getAttribute("userBean"))
                .isConnected()) {
            req.getSession()
                    .setAttribute("afterLoginGoTo", req.getRequestURL());
            resp.sendRedirect(contextPath + "/Login");
            return;
        }
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        contextPath = fConfig.getServletContext().getContextPath();
    }

}
