package com.tap.adminservlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.DeliveryDaoImpl;
import com.tap.model.DeliveryItems;

@WebServlet("/assign_order")
public class AssignOrderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
           String orderId =  req.getParameter("orderId");
         //  int orderItemId = (int) session.getAttribute("orderItemId");
           System.out.println(orderId);

            String agentName = req.getParameter("agentName");
            String password = req.getParameter("password");
            int OTP = Integer.parseInt(req.getParameter("otp"));

            DeliveryItems di = new DeliveryItems(agentName, OTP, orderId,0, password);

            DeliveryDaoImpl ddi = new DeliveryDaoImpl();
            System.out.println(ddi);
            ddi.addDeliveryOrders(di);
            System.out.println(di);
            
            session.setAttribute("ddi", di);
            
            RequestDispatcher rd=req.getRequestDispatcher("admin/PreparingOrdersView.jsp");
            rd.forward(req, resp);
            
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/admin/AdminHome.jsp");
        }
    }
}
