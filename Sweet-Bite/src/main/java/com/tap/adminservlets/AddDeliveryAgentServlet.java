package com.tap.adminservlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.UserDaoImpl;
import com.tap.model.User;

@WebServlet("/deliveryAgent")
public class AddDeliveryAgentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            // Retrieve form data
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String phoneNo = req.getParameter("phoneNo");
            String address = req.getParameter("address");
            String userName = req.getParameter("userName");
            String password = req.getParameter("password");
            String role = req.getParameter("role");

            // Create a User object with the retrieved data
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPhoneNo(Long.parseLong(phoneNo));
            user.setAddress(address);
            user.setUserName(userName);
            user.setPassword(password);
            user.setRole(role);

            // Add the user to the database using UserDaoImpl
            UserDaoImpl userDao = new UserDaoImpl();
            boolean success = userDao.addUser(user);

            if (success) {
                session.setAttribute("succmsg", "Delivery Agent added successfully");
            } else {
                session.setAttribute("failmsg", "Failed to add Delivery Agent");
            }

            resp.sendRedirect("admin/DeliveryAgent.jsp");

        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            // Handle the exception gracefully, for example, redirect to an error page
            session.setAttribute("failmsg", "Failed to add Delivery Agent");
            resp.sendRedirect("admin/DeliveryAgent.jsp");
        }
    }
}
