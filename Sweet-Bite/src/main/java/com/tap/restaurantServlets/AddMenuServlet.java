package com.tap.restaurantServlets;

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

import com.tap.daoImpl.MenuDaoImpl;
import com.tap.daoImpl.RestaurantDaoImpl;
import com.tap.daoImpl.UserDaoImpl;
import com.tap.model.Menu;
import com.tap.model.Restaurant;
import com.tap.model.User;

@WebServlet("/addMenuServletbyres")
@MultipartConfig
public class AddMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        try {
            String action = request.getParameter("action");
            System.out.println(action);
            
            if (action.equals("Remove Restaurant")) {
                // Handle removal of the restaurant
                HttpSession session = request.getSession();
                User user = (User)session.getAttribute("userobj");
                
               
                
                RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
                Restaurant restaurant = restaurantDao.getRestaurantByOwner(user.getUserId());
                
                MenuDaoImpl menuDao = new MenuDaoImpl();
                menuDao.deleteMenuByRes(restaurant.getRestaurantId());
                boolean success = restaurantDao.deleteRestaurant(restaurant.getRestaurantId());

                if (success) {
                    session.setAttribute("succmsg", "Restaurant removed successfully");
                    response.sendRedirect("RestaurantOwner/restaurantHome.jsp");
                } else {
                    session.setAttribute("failmsg", "Something went wrong");
                    response.sendRedirect("RestaurantOwner/restaurantHome.jsp");
                }
            } else if (action.equals("Add Menu")) {
                // Handle addition of menu
                String menuName = request.getParameter("menuName");
                String price = request.getParameter("price");
                String description = request.getParameter("description");
                 
                Part part = request.getPart("imagePath");
                String fileName = part.getSubmittedFileName();
                HttpSession session = request.getSession();
                
                User user = (User)session.getAttribute("userobj");
                System.out.println(user);
                RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
                Restaurant restaurant = restaurantDao.getRestaurantByOwner(user.getUserId());
                System.out.println(restaurant);
                
                
                Menu menu = new Menu(menuName, Double.parseDouble(price), description, restaurant.getRestaurantId(), fileName);
                MenuDaoImpl menuDao = new MenuDaoImpl();
                boolean success = menuDao.addMenu(menu);

                if (success) {
                    String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    // Save the uploaded image to the server
                    part.write(uploadPath + File.separator + fileName);

                    session.setAttribute("succmsg", "Menu added successfully");
                    response.sendRedirect("RestaurantOwner/add_menu_res.jsp");
                } else {
                    session.setAttribute("failmsg", "Something went wrong");
                    response.sendRedirect("/RestaurantOwner/add_menu_res.jsp");
                }
            } else {
            	HttpSession session = request.getSession();
                User user = (User)session.getAttribute("userobj");
                
                String menuName = request.getParameter("menuname");
                


                RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
                MenuDaoImpl menuDao = new MenuDaoImpl();

                Restaurant restaurant = restaurantDao.getRestaurantByOwner(user.getUserId());

                menuDao.deleteMenuByResAndName(restaurant.getRestaurantId(), menuName);

                response.sendRedirect("/Sweet-Bite/RestaurantOwner/remove_menu.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            HttpSession session = request.getSession();
            session.setAttribute("failmsg", "u have removed the restaurant again register your restaurant");
            response.sendRedirect("RestaurantOwner/add_menu_res.jsp");
        }
    }
}
