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

import com.xqvier.webmarket.web.util.ResponseWrapper;

/**
 * Servlet Filter implementation class DefaultFilter
 */
@WebFilter("/*")
public class DefaultFilter implements Filter {

    /**
     * Default constructor.
     */
    public DefaultFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        System.out.println("Utilisateur " + req.getRemoteHost()
                + " tried to access the resource " + req.getRequestURL());

        ResponseWrapper responseWrapper = new ResponseWrapper(resp);

        // pass the request along the filter chain
        chain.doFilter(request, responseWrapper);

        //String modifiedResponse = addFooter(responseWrapper.toString());

        // response.getWriter().write(modifiedResponse);
        response.getWriter().write(responseWrapper.toString());

    }

    /**
     * Ajoute un footer à la réponse HTTP envoyée au client.
     * 
     * @param string
     *            La réponse à surcharger.
     */
//    private static String addFooter(String string) {
//        return string + "<footer>Copyright ©</footer>";
//    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {

    }

}
