package com.tap.deliveryagentservlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.DeliveryDaoImpl;
import com.tap.daoImpl.OrderDaoImpl;
import com.tap.daoImpl.OrderHistoryDaoImpl;
import com.tap.model.DeliveryItems;
import com.tap.model.Order;
import com.tap.model.OrderHistory;
import com.tap.model.User;

@WebServlet("/updateStatus")
public class UpdateOrderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
                System.out.println("yes");
              String action=  req.getParameter("action");
           System.out.println(action);
           
                OrderHistoryDaoImpl orderHistoryDao = new OrderHistoryDaoImpl();
               

            if (action.equals("history")) {
            	
            User user=(User)	session.getAttribute("userobj");
            	
               DeliveryDaoImpl ddi=new DeliveryDaoImpl();
            	List<DeliveryItems> list=ddi.getAllDeliveryOrdersByAgent(user.getUserName(), user.getPassword());

                session.setAttribute("deliveryItems", list);

                RequestDispatcher rd = req.getRequestDispatcher("DeliveryAgent/AgentDeliveryHistory.jsp");
                rd.forward(req, resp);
            } else {
            	 String orderId =req.getParameter("orderId");
                 System.out.println(orderId);
                 String status = req.getParameter("status");
                 String ETA=req.getParameter("eta");
                 System.out.println(ETA);
                 OrderDaoImpl orderDao = new OrderDaoImpl();

                 Order order = orderDao.getOrder(orderId);
                 order.setStatus(status);
                 order.setETA(ETA);
                orderDao.updateOrder(order);

                RequestDispatcher rd = req.getRequestDispatcher("deliveryServlet");
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
            // Redirect to an error page or handle gracefully
            resp.sendRedirect("AgentDeliveryHistory.jsp");
        }
    }
}
