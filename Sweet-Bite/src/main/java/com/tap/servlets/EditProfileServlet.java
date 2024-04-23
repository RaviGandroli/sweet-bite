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

@WebServlet("/UserUpdate")
public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UserDaoImpl userDao = new UserDaoImpl();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userobj");

            if (user != null) {
                String action = request.getParameter("action");
                if (action != null && action.equals("updateProfile")) {
                    String address = request.getParameter("address");
                    String phoneNumber = request.getParameter("phoneno");
                    
                    // Update user's address and phone number
                    user.setAddress(address);
                    user.setPhoneNo(Long.parseLong(phoneNumber));
                    userDao.updateUser(user);

                    // Update session attribute
                    session.setAttribute("userobj", user);

                    // Redirect to Checkout.jsp
                    response.sendRedirect("Checkout.jsp");
                } else {
                    // Handle updating user profile

                    String page=request.getParameter("name");
                    
                    int id = Integer.parseInt(request.getParameter("id"));
                    String name = request.getParameter("username");
                    String email = request.getParameter("email");
                    String password=request.getParameter("password");
                    String phoneno = request.getParameter("phoneno");
                    String address = request.getParameter("address");

                    // Update user's profile details
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setPhoneNo(Long.parseLong(phoneno));
                    user.setAddress(address);
                    userDao.updateUser(user);
                  
                    session.setAttribute("userobj", user);
                  
                    session.setAttribute("succmsg", "Updated your details successfully");
                    
                    // Redirect based on the 'page' parameter
                    String redirectPage = null;
                    if(page.equals("editprofile")) {
                        redirectPage = "EditProfile.jsp";
                    }
                    else if(page.equals("editprofileres")) {
                        redirectPage = "/Sweet-Bite/RestaurantOwner/EditProfileRes.jsp";
                    }
                    else {
                        redirectPage = "/Sweet-Bite/DeliveryAgent/EditProfileAgent.jsp";
                    }
                    response.sendRedirect(redirectPage);
                }
            } else {
                // Handle case where user object is not found in session
                session.setAttribute("failmsg", "Invalid input format");
                response.sendRedirect("EditProfile.jsp");
            }
        } catch (NumberFormatException e) {
            // Handle invalid phone number format
            HttpSession session = request.getSession();
            session.setAttribute("failmsg", "Invalid input format");
            response.sendRedirect("EditProfile.jsp");
        } catch (Exception e) {
            // Handle other exceptions properly
            e.printStackTrace();
            HttpSession session = request.getSession();
            session.setAttribute("failmsg", "An error occurred while processing your request");
            response.sendRedirect("EditProfile.jsp");
        }
    }
}
