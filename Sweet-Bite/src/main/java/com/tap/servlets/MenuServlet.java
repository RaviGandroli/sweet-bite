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
import com.tap.model.Menu;

@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String Id = req.getParameter("restaurantId");
            HttpSession session = req.getSession();
            MenuDaoImpl mdi = new MenuDaoImpl();

            int restaurantId = Integer.parseInt(Id);

            List<Menu> menus = mdi.getAllMenu(restaurantId);

            session.setAttribute("menus", menus);
            session.setAttribute("restaurantId", Id);

            RequestDispatcher rd = req.getRequestDispatcher("Menu.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Redirect to an error page or handle the error in some way
            resp.sendRedirect("Menu.jsp");
        }
    }
}
