package com.xqvier.webmarket.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqvier.webmarket.business.core.DAOFactory;
import com.xqvier.webmarket.web.pojo.Cart;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
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
        if (request.getParameter("addId") != null) {
            ((Cart) request.getSession().getAttribute("panier")).getProducts()
                    .add(DAOFactory.getProductDAO().find(
                            Long.parseLong(request.getParameter("addId"))));
        }

        if (request.getParameter("removeId") != null) {
            ((Cart) request.getSession().getAttribute("panier")).getProducts()
                    .remove(Integer.parseInt(request.getParameter("removeId")));
        }

        getServletContext().getRequestDispatcher("/home.jsp").forward(request,
                response);
    }

}
