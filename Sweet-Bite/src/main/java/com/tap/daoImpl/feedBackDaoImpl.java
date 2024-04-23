package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tap.dao.feedBackDao;
import com.tap.model.Order;
import com.tap.model.userFeedBack;

public class feedBackDaoImpl implements feedBackDao{

	
	 static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
	    static private final String USERNAME = "root";
	    static private final String PASSWORD = "Mr@840420";
	    Connection conn = null;

	public feedBackDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addFeedBack(userFeedBack feedback) {
        
        String sql = "INSERT INTO `feedback` (`userId`, `feedback`) VALUES (?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean f=false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, feedback.getUserId());
            pstmt.setString(2, feedback.getFeedBack());
            

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 1) {
               f=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return f;
    }
	
}
