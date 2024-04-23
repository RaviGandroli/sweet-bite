package com.tap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.UserDaoImpl;
import com.tap.model.User;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("fullname");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            String username = req.getParameter("username");

            UserDaoImpl userDao = new UserDaoImpl();
            User user = new User();
            HttpSession session = req.getSession();

            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setUserName(username);

            if (userDao.addUser(user)) {
                session.setAttribute("user", user);
                resp.sendRedirect("Login.jsp");
            } else {
                resp.sendRedirect("Login.jsp");
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Redirect to an error page or handle the error in some way
            resp.sendRedirect("Login.jsp");
        }
    }
}
