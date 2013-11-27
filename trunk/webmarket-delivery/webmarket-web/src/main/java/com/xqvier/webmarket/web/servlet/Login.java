package com.xqvier.webmarket.web.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xqvier.webmarket.common.service.ClientServiceLocal;
import com.xqvier.webmarket.web.bean.UserBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ClientServiceLocal clientServiceLocal;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
        if (request.getParameter("username") != null
                && request.getParameter("password") != null) {
            String userName = request.getParameter("username");
            String password = request.getParameter("password");
            if (clientServiceLocal.checkUserPassword(userName, password)) {

                UserBean userBean = (UserBean) request.getSession()
                        .getAttribute("userBean");
                userBean.setClient(clientServiceLocal.readClient(userName,
                        password));

                userBean.setConnected(true);
                if (request.getSession().getAttribute("afterLoginGoTo") != null) {
                    response.sendRedirect(request.getSession()
                            .getAttribute("afterLoginGoTo").toString());
                    return;
                }
                response.sendRedirect(getServletContext().getContextPath()
                        + "/Home");
                return;
            }
            response.sendRedirect(getServletContext().getContextPath()
                    + "/Login");
            return;
        }

        getServletContext().getRequestDispatcher("/login.jsp").forward(request,
                response);

    }
}
