package com.tap.adminservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.OrderDaoImpl;
import com.tap.daoImpl.OrderItemDaoImpl;

@WebServlet("/viewOrders")
public class ViewOrderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String href = req.getParameter("href");

            OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
            OrderDaoImpl orderDao = new OrderDaoImpl();

            HttpSession session = req.getSession();
            session.setAttribute("viewOrderItem", orderItemDao.getAllOrderItems());
            session.setAttribute("viewOrders", orderDao.getAllOrder());
            
            System.out.println(session.getAttribute("viewOrderItem"));
            
            

            if (href != null) {
                RequestDispatcher rd = null;
                switch (href) {
                    case "prepareOrder":
                        rd = req.getRequestDispatcher("admin/PreparingOrdersView.jsp");
                        break;
                    case "dispatchedOrders":
                        rd = req.getRequestDispatcher("admin/DispatchedOrdersView.jsp");
                        break;
                    case "onTheWayOrder":
                        rd = req.getRequestDispatcher("admin/Ontheway_Orders_view.jsp");
                        break;
                    case "deliveredOrder":
                        rd = req.getRequestDispatcher("admin/DliveredOrdersView.jsp");
                        break;
                    default:
                        // If invalid href is provided, redirect to error page
                        rd = req.getRequestDispatcher("AdminHome.jsp");
                        break;
                }
                if (rd != null) {
                    rd.forward(req, resp);
                }
            }
        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
            // Redirect to an error page or handle gracefully
            resp.sendRedirect("/admin/AdminHome.jsp");
        }
    }
}
