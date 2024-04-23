package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tap.dao.SettingsDao;
import com.tap.model.Setting;

public class SettingDaoImpl implements SettingsDao {

    static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    static private final String USERNAME = "root";
    static private final String PASSWORD = "Mr@840420";
    Connection conn = null;

    public SettingDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            handleException(e);
        }
    }

    @Override
    public boolean updateSettings(Setting settings, int userId) {
        boolean f = false;
        String sql = "UPDATE setting SET language=?, theme=?, privacy=?, email_notification=? where userId=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, settings.getLanguage());
            pstmt.setString(2, settings.getTheme());
            pstmt.setString(3, settings.getPrivacy());
            pstmt.setString(4, settings.getEmailNotification());
            pstmt.setInt(5, userId);

            int i = pstmt.executeUpdate();

            if (i == 1) {
                f = true;
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return f;
    }

    @Override
    public boolean addSetting(Setting settings, int userId) {
        boolean f = false;
        String sql = "INSERT into setting(language, theme, privacy, email_notification,userId) values (?,?,?,?,?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, settings.getLanguage());
            pstmt.setString(2, settings.getTheme());
            pstmt.setString(3, settings.getPrivacy());
            pstmt.setString(4, settings.getEmailNotification());
            pstmt.setInt(5, userId);

            int i = pstmt.executeUpdate();

            if (i == 1) {
                f = true;
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return f;
    }

    @Override
    public Setting getSetting(int userId) {
        String sql = "select * from setting where userId=?";
        Setting setting = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String language = rs.getString("language");
                String theme = rs.getString("theme");
                String privacy = rs.getString("privacy");
                String emailNotification = rs.getString("email_notification");
                setting = new Setting(language, theme, privacy, emailNotification);
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return setting;
    }

    private void handleException(Exception e) {
        e.printStackTrace();
        // Log the exception or perform any other necessary actions.
    }

    public void closeResources() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            handleException(e);
        }
    }
}
