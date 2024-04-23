package com.tap.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tap.daoImpl.SettingDaoImpl;
import com.tap.model.Setting;
import com.tap.model.User;

@WebServlet("/updateSettings")
public class updateSetting extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String language = request.getParameter("language");
            String theme = request.getParameter("theme");
            String privacy = request.getParameter("privacy");
            String emailNotification = request.getParameter("emailNotification");

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("userobj");

            Setting setting = new Setting(language, theme, privacy, emailNotification);

            SettingDaoImpl settingDao = new SettingDaoImpl();
            Setting settingFound = settingDao.getSetting(user.getUserId());

            if (settingFound != null) {
                settingDao.updateSettings(setting, user.getUserId());
            } else {
                settingDao.addSetting(setting, user.getUserId());
            }
            session.setAttribute("succmsg", "Updated your details successfully");
            response.sendRedirect("Setting.jsp");
        } catch (IOException e) {
            // Log the exception
            e.printStackTrace();
            // Set error message in session and redirect
            HttpSession session = request.getSession();
            session.setAttribute("failmsg", "An error occurred please waite our team is on resolving the issue ");
            response.sendRedirect("Setting.jsp");
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();
            // Set error message in session and redirect
            HttpSession session = request.getSession();
            session.setAttribute("failmsg", "An unexpected error occurred");
            response.sendRedirect("Setting.jsp");
        }
    }
}
