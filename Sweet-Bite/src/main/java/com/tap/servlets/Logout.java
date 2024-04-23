package com.tap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logoutservlet")
public class Logout extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            session.removeAttribute("userobj");
            session.setAttribute("succmsg", "Logout Successfully");
            resp.sendRedirect("Login.jsp");
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Redirect to an error page or handle the error in some way
            resp.sendRedirect("errorPage.jsp");
        }
    }
}
