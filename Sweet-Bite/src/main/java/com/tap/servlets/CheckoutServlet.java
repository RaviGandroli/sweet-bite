package com.tap.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.OrderDaoImpl;
import com.tap.daoImpl.OrderHistoryDaoImpl;
import com.tap.daoImpl.OrderItemDaoImpl;
import com.tap.model.CartCreator;
import com.tap.model.CartItem;
import com.tap.model.Menu;
import com.tap.model.Order;
import com.tap.model.OrderHistory;
import com.tap.model.OrderItem;
import com.tap.model.User;

@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	 HttpSession session=req.getSession();
    	try {
        	
            String paymentType = req.getParameter("payment");
            
          
            int restaurantId = Integer.parseInt((String) session.getAttribute("restaurantId"));
            User user = (User) session.getAttribute("userobj");

            Map<Integer, Double> restaurantPrices = new HashMap<>();

            OrderDaoImpl orderDao = new OrderDaoImpl();
            Order order = new Order(0.0, paymentType, "priparing", restaurantId, user.getUserId(),"30 min");
           
            String orderId = orderDao.addOrder(order);
           
            
            
            CartCreator cartCreator = (CartCreator) session.getAttribute("cart");
            
            if (cartCreator != null) {
                Map<Integer, CartItem> cartItems = cartCreator.getAllItems();
                Set<Entry<Integer, CartItem>> entrySet = cartItems.entrySet();
                Iterator<Entry<Integer, CartItem>> iterator = entrySet.iterator();
                while (iterator.hasNext()) {
                    Entry<Integer, CartItem> entry = iterator.next();
                    CartItem cartItem = entry.getValue();

                    int menuRestaurantId = cartItem.getRestaurantId();
                    double price = cartItem.getPrice();

                    OrderItem orderItem = new OrderItem(cartItem.getItemId(), user.getUserId(), cartItem.getName(), cartItem.getRating(), cartItem.getQuantity(), cartItem.getPrice(),orderId);
                    OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
                    orderItemDao.addOrderItem(orderItem);

                    restaurantPrices.put(menuRestaurantId, restaurantPrices.getOrDefault(menuRestaurantId, 0.0) + price);
                }
                
                
                OrderHistoryDaoImpl ohdi=new OrderHistoryDaoImpl();
                OrderHistory oh=new OrderHistory(orderId,user.getUserId());
             
                ohdi.addOrderHistory(oh);
                
                
                double totalAmount = restaurantPrices.getOrDefault(restaurantId, 0.0);
              
                order.setTotalAmout(totalAmount);

                // Update the order with the calculated total amount
                order.setOrderId(orderId); // Ensure orderId is set
                boolean updateSuccess = orderDao.updateOrder(order);
                
                

              
                session.setAttribute("updateStatus", updateSuccess);
                if(updateSuccess) {
                	session.removeAttribute("cart");
                }
            }
            
            
            RequestDispatcher rd = req.getRequestDispatcher("OrderTracking.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Redirect to an error page or handle it in another way
           
            session.setAttribute("failmsg", "plese  fill your details if u r not registered register");
            resp.sendRedirect("Checkout.jsp");
        }
    }
}
