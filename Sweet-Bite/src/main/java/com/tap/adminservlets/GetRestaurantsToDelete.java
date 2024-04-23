package com.tap.adminservlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tap.daoImpl.RestaurantDaoImpl;
import com.tap.model.Restaurant;

@WebServlet("/removerestaurant")
public class GetRestaurantsToDelete extends HttpServlet {

	 @Override
	    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        try {
	            RestaurantDaoImpl restaurantDao = new RestaurantDaoImpl();
	            List<Restaurant> restaurants = restaurantDao.getAllRestaurant();

	            req.setAttribute("restaurants", restaurants);
	            
	            // Forward the request to the index.jsp page
	            RequestDispatcher rd = req.getRequestDispatcher("admin/remove_restaurant.jsp");
	            rd.include(req, resp);
	        } catch (Exception e) {
	            // Log the exception
	            e.printStackTrace();
	            // Redirect to an error page or handle the error in some way
	            resp.sendRedirect("/admin/AdminHome.jsp");
	        }
	    }
}
