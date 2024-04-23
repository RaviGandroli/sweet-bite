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
import com.tap.model.DeliveryItems;
import com.tap.model.User;

@WebServlet("/deliveryServlet")
public class GetOrdersServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("userobj");

            DeliveryDaoImpl deliveryDao = new DeliveryDaoImpl();
            List<DeliveryItems> deliveryItems = deliveryDao.getAllDeliveryOrdersByAgent(user.getUserName(), user.getPassword());
            System.out.println(deliveryItems);
            session.setAttribute("deliveryItems", deliveryItems);
            
            
            String action=req.getParameter("action");
            System.out.println(action);
            
           if(action.equals("history")) {
            RequestDispatcher rd = req.getRequestDispatcher("DeliveryAgent/AgentDeliveryHistory.jsp");
            rd.forward(req, resp);
           }
           else {
        	   System.out.println(session.getAttribute("deliveryItems"));
        	   RequestDispatcher rd = req.getRequestDispatcher("DeliveryAgent/DeliveryAgentHome.jsp");
               rd.forward(req, resp);
           }
        } catch (Exception e) {
            // Handle any exceptions here
            e.printStackTrace();
            // Redirect to an error page or handle gracefully
            resp.sendRedirect("DeliveryAgentHome.jsp");
        }
    }
}
