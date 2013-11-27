package com.xqvier.webmarket.web.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqvier.webmarket.common.service.ProductServiceLocal;
import com.xqvier.webmarket.web.bean.ProductBean;
import com.xqvier.webmarket.web.pojo.Cart;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB(beanInterface = ProductServiceLocal.class)
    private ProductServiceLocal productServiceLocal;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        Cart panier = (Cart) request.getSession().getAttribute("cart");

        ProductBean productBean = (ProductBean) getServletContext().getAttribute("productBean");
        if(productBean.getProducts() == null){
            productBean.setProducts(productServiceLocal.findAll());
        }
        
        
        if (request.getParameter("addId") != null) {
            panier.getProducts().add(
                    productServiceLocal.find(Long.parseLong(request
                            .getParameter("addId"))));
        }

        if (request.getParameter("removeId") != null) {
            panier.getProducts().remove(
                    productServiceLocal.find(Long.parseLong(request
                            .getParameter("removeId"))));
        }

        response.sendRedirect(getServletContext().getContextPath() + "/home.jsp");
    }

}
