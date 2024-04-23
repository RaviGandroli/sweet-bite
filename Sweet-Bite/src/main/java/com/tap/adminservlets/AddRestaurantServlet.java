package com.tap.adminservlets;

import com.tap.daoImpl.MenuDaoImpl;
import com.tap.daoImpl.RestaurantDaoImpl;
import com.tap.daoImpl.UserDaoImpl;
import com.tap.model.Restaurant;
import com.tap.model.User;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/addRestaurants")
@MultipartConfig
public class AddRestaurantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        try {
            if (action != null) {
                if (action.equals("AddRestaurant")) {
                    String restaurantName = request.getParameter("restaurantName");
                    String cuisineType = request.getParameter("cuisineType");
                    String address = request.getParameter("address");
                    Part part = request.getPart("image");
                    String fileName = part.getSubmittedFileName();
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    UserDaoImpl userDao = new UserDaoImpl();
                    User user = userDao.checkLogin(username, password);

                    if (user != null) {
                        user.setRole("restaurantOwener");
                        userDao.updateUser(user);

                        Restaurant restaurant = new Restaurant(restaurantName, fileName, cuisineType, address, user.getUserId());

                        RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
                        boolean success = restaurantDao.addRestaurant(restaurant);

                        if (success) {
                            String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
                            File uploadDir = new File(uploadPath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdir();
                            }

                            // Save the uploaded image to the server
                            part.write(uploadPath + File.separator + fileName);

                            session.setAttribute("succmsg", "Restaurant added successfully");
                            session.setAttribute("resOwnerId", user.getUserId());
                            response.sendRedirect("/Sweet-Bite/admin/add_restaurant.jsp");
                        } else {
                            session.setAttribute("failmsg", "Failed to add restaurant");
                            response.sendRedirect("/Sweet-Bite/admin/add_restaurant.jsp");
                        }
                    } else {
                        session.setAttribute("failmsg", "Invalid username or password");
                        response.sendRedirect("/Sweet-Bite/admin/add_restaurant.jsp");
                    }
                } else if (action.equals("RemoveRestaurant")) {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");

                    UserDaoImpl userDao = new UserDaoImpl();
                    User user = userDao.checkLogin(username, password);

                    if (user != null) {
                        RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
                        MenuDaoImpl menuDao = new MenuDaoImpl();

                        Restaurant restaurant = restaurantDao.getRestaurantByOwner(user.getUserId());
                        if (restaurant != null) {
                            menuDao.deleteMenuByRes(restaurant.getRestaurantId());
                            boolean success = restaurantDao.deleteRestaurant(restaurant.getRestaurantId());

                            if (success) {
                                session.setAttribute("succmsg", "Restaurant removed successfully");
                            } else {
                                session.setAttribute("failmsg", "Failed to remove restaurant");
                            }
                        } else {
                            session.setAttribute("failmsg", "No restaurant found for the owner");
                        }
                    } else {
                        session.setAttribute("failmsg", "Invalid username or password");
                    }

                    response.sendRedirect("/Sweet-Bite/admin/remove_restaurant.jsp");
                } else {
                    session.setAttribute("failmsg", "Invalid action specified");
                    response.sendRedirect("/Sweet-Bite/admin/add_restaurant.jsp");
                }
            } else {
                session.setAttribute("failmsg", "No action specified");
                response.sendRedirect("/Sweet-Bite/admin/add_restaurant.jsp");
            }
        } catch (IOException | ServletException e) {
            session.setAttribute("failmsg", "An error occurred: " + e.getMessage());
            response.sendRedirect("/Sweet-Bite/admin/add_restaurant.jsp");
        } catch (Exception e) {
            session.setAttribute("failmsg", "An unexpected error occurred");
            response.sendRedirect("/Sweet-Bite/admin/add_restaurant.jsp");
        }
    }
}
