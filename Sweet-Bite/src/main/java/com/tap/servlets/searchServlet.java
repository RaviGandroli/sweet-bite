package com.tap.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.MenuDaoImpl;
import com.tap.daoImpl.RestaurantDaoImpl;
import com.tap.model.Menu;
import com.tap.model.Restaurant;

@WebServlet("/searchServlet")
public class searchServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve the search term and search type from the request parameters
        String searchTerm = req.getParameter("ch");
        String searchType = req.getParameter("searchType");
        System.out.println(searchTerm+" "+searchType);
System.out.println(" i am inside search");
        // You can now use the searchTerm and searchType variables as needed
        // For example, you can pass them to a service or DAO class to perform the search
        HttpSession session=req.getSession();
        
        // Example:
        if ("menu".equals(searchType)) {
            // Search for menu items using searchTerm
            // Call your method or service here
        	MenuDaoImpl mdi=new MenuDaoImpl();
        	List<Menu> menues=mdi.getAllMenuBySearch(searchTerm);
            session.setAttribute("menus", menues);
            RequestDispatcher rd = req.getRequestDispatcher("Menu.jsp");
            rd.include(req, resp);

        } else if ("restaurant".equals(searchType)) {
            // Search for restaurants using searchTerm
            // Call your method or service here
            RestaurantDaoImpl rdi=new RestaurantDaoImpl();

        	List<Restaurant> restaurants=rdi.getRestaurantBySearch(searchTerm);
        	System.out.println(restaurants);
            session.setAttribute("restaurants", restaurants);
            List<Restaurant> restaurant=(List<Restaurant>)session.getAttribute("restaurants");
            System.out.println(restaurant);
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.include(req, resp);
        }
        else {
        	session.setAttribute("failmsg", "please select menuitem or restaurant");
        	resp.sendRedirect("index.jsp");
        }
    }
}
