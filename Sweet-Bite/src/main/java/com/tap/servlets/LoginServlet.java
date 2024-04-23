package com.tap.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.UserDaoImpl;
import com.tap.model.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String action = req.getParameter("action");

            UserDaoImpl userDao = new UserDaoImpl();
            HttpSession session = req.getSession();

           

            User user = userDao.checkLogin(username, password);

            if (user != null) {
                if (username.equals("RAVI4-GANDROLI8") && password.equals("024048RM@MR840420")) {
                    session.setAttribute("userobj", user);
                    RequestDispatcher rd = req.getRequestDispatcher("/admin/AdminHome.jsp");
                    rd.forward(req, resp);
                } else if (user.getRole().equals("deliveryBoy")) {
                    session.setAttribute("userobj", user);
                    RequestDispatcher rd = req.getRequestDispatcher("deliveryServlet");
                    rd.forward(req, resp);
                } else if(user.getRole().equals("restaurantOwener")){
                	session.setAttribute("userobj", user);
                    RequestDispatcher rd = req.getRequestDispatcher("/RestaurantOwner/restaurantHome.jsp");
                    rd.forward(req, resp);

                }
                	else{
                
                    session.setAttribute("userobj", user);
                    RequestDispatcher rd = req.getRequestDispatcher("RestaurantServlet");
                    rd.forward(req, resp);
                }
            } else {
                session.setAttribute("failmsg", "Email & password invalid");
                resp.sendRedirect("Login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Redirect to an error page or handle the error in some way
            resp.sendRedirect("errorPage.jsp");
        }
    }
}
