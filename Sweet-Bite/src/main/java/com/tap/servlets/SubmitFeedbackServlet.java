package com.tap.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.feedBackDaoImpl;
import com.tap.model.User;
import com.tap.model.userFeedBack;

@WebServlet("/SubmitFeedbackServlet")
public class SubmitFeedbackServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("userobj");
            String feedback = req.getParameter("feedback");

            // Validate feedback (if necessary)

            userFeedBack ufb = new userFeedBack(user.getUserId(), feedback);

            feedBackDaoImpl fbi = new feedBackDaoImpl();
            fbi.addFeedBack(ufb);

            // Redirect the user to UserProfile.jsp after successful feedback submission
            RequestDispatcher rd = req.getRequestDispatcher("UserProfile.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            // Log the exception (consider using a logging framework like Log4j)
            e.printStackTrace();

            // Redirect the user to an error page
            HttpSession session = req.getSession();
            session.setAttribute("failmsg", "An unexpected error occurred");
            resp.sendRedirect("UserProfile.jsp");
        }
    }
}
