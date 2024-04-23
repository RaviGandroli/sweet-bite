package com.tap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.MenuDaoImpl;
import com.tap.model.CartCreator;
import com.tap.model.CartItem;
import com.tap.model.Menu;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        	
        
            HttpSession session = req.getSession();
           CartCreator cartCreator = (CartCreator) session.getAttribute("cart");
            
            
          
            
            
            if (cartCreator == null) {
                cartCreator = new CartCreator();
                session.setAttribute("cart", cartCreator);
            }
            
            String menuIds = req.getParameter("menuId");
            String action = req.getParameter("action");

            int menuId = Integer.parseInt(menuIds);

            MenuDaoImpl menuDao = new MenuDaoImpl();
            Menu menu = menuDao.getMenu(menuId);

            if (action.equals("add")) {
                String quantityS = req.getParameter("quantity");
                int quantity=0;
                if(quantityS==null) {
                	quantity=1;
                }
                else {
                 quantity = Integer.parseInt(quantityS);
                }
                CartItem cartItem = new CartItem(menuId, menu.getRestaurantId(), menu.getMenuName(), menu.getPrice(),menu.getRatings() ,quantity, menu.getImagepath());
                cartCreator.addCartItem(cartItem);
            } else if (action.equals("update")) {
                String quantityS = req.getParameter("quantity");
                int quantity = Integer.parseInt(quantityS);
                cartCreator.updateCartItem(menuId, quantity, menu.getPrice());
            } else if (action.equals("increase")) {
                cartCreator.increaseCartItem(menuId, menu.getPrice());
            } else if (action.equals("decrease")) {
                cartCreator.decreaseCartItem(menuId, menu.getPrice());
            } else if (action.equals("remove")) {
                cartCreator.removeCartItem(menuId);
            }
            double totalAmount = cartCreator.getTotalAmount();
            session.setAttribute("totalAmount", totalAmount);


            // Redirect to viewCart.jsp
            resp.sendRedirect("viewCart.jsp");
        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
            // Redirect to an error page or handle gracefully
            resp.sendRedirect("viewCart.jsp");
        }
    }
}
