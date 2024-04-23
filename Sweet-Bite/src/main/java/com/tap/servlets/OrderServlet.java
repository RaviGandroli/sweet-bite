package com.tap.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.OrderDaoImpl;
import com.tap.daoImpl.OrderItemDaoImpl;
import com.tap.model.Order;
import com.tap.model.OrderItem;
import com.tap.model.User;

@WebServlet("/ordersHistoryServlet")
public class OrderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("userobj");

            if (user != null) {
                OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
                OrderDaoImpl orderDao = new OrderDaoImpl();

                List<OrderItem> orderItems = orderItemDao.getOrderItemByUser(user.getUserId());
                List<Order> orders = orderDao.getOrderByUser(user.getUserId());

                session.setAttribute("viewOrderItem", orderItems != null ? orderItems : new ArrayList<OrderItem>());
                session.setAttribute("viewOrders", orders != null ? orders : new ArrayList<Order>());
                  
                String action=req.getParameter("action");
                if(action.equals("viewOrders")) {
                    RequestDispatcher rd = req.getRequestDispatcher("onWayOrders.jsp");
                    rd.forward(req, resp);
                }
                else {
                RequestDispatcher rd = req.getRequestDispatcher("OrderHistory.jsp");
                rd.forward(req, resp);
                }
            } else {
                // Handle case where user object is not found in session
                // Redirect to an error page or handle the error in some way
                resp.sendRedirect("OrderHistory.jsp");
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Redirect to an error page or handle the error in some way
            resp.sendRedirect("OrderHistory.jsp");
        }
    }
}
