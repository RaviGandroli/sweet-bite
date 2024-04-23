package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tap.dao.OrderDao;
import com.tap.model.Order;

public class OrderDaoImpl implements OrderDao {

    static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    static private final String USERNAME = "root";
    static private final String PASSWORD = "Mr@840420";
    Connection conn = null;

    public OrderDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static String generateOrderId() {
        // Get the current timestamp in milliseconds
        long timestamp = System.currentTimeMillis();
        
        // Generate a random 6-digit numeric suffix
        Random random = new Random();
        int suffix = random.nextInt(900000) + 100000; // Generates a random number between 100000 and 999999
        
        // Combine timestamp with the random suffix
        String orderId = Long.toString(timestamp) + Integer.toString(suffix);
        
        return orderId;
    }
    
    
    @Override
    public String addOrder(Order order) {
    	System.out.println("i am in add");
         // Initialize orderId to a default value indicating failure
        String sql = "INSERT INTO `order` (`orderId`,`totalAmout`, `modeOfPayment`, `status`, `restaurantId`, `userId`) VALUES (?,?, ?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String orderId=generateOrderId();
         
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);
            pstmt.setDouble(2, order.getTotalAmout());
            pstmt.setString(3, order.getModeOfPayment());
            pstmt.setString(4, order.getStatus());
            pstmt.setInt(5, order.getRestaurantId());
            pstmt.setInt(6, order.getUserId());

            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	closeResources(rs, pstmt);
        }

        return orderId;
    }


    @Override
    public Order getOrder(String orderId) {
        String sql = "SELECT * FROM `order` WHERE orderId=?";
        Order o = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                orderId = rs.getString("orderId");
                double totalAmout = rs.getDouble("totalAmout");
                String modeOfPayment = rs.getString("modeOfPayment");
                String status = rs.getString("status");
                int restaurantId = rs.getInt("restaurantId");
                int userId = rs.getInt("userId");
                String ETA=rs.getString("ETA");
                o = new Order(orderId, totalAmout, modeOfPayment, status, restaurantId, userId,ETA);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pstmt);
        }

        return o;
    }

    @Override
    public List<Order> getOrderByUser(int userId) {
        String sql = "SELECT * FROM `order` WHERE userId=?";
        ArrayList<Order> list = new ArrayList<>();
        Order o = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String orderId = rs.getString("orderId");
                double totalAmout = rs.getDouble("totalAmout");
                String modeOfPayment = rs.getString("modeOfPayment");
                String status = rs.getString("status");
                int restaurantId = rs.getInt("restaurantId");
                userId = rs.getInt("userId");
                String ETA=rs.getString("ETA");
                o = new Order(orderId, totalAmout, modeOfPayment, status, restaurantId, userId,ETA);
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pstmt);
        }

        return list;
    }

    @Override
    public List<Order> getAllOrder() {
        String sql = "SELECT * FROM `order` ";
        Order o = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Order> list = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String orderId = rs.getString("orderId");
                double totalAmout = rs.getDouble("totalAmout");
                String modeOfPayment = rs.getString("modeOfPayment");
                String status = rs.getString("status");
                int restaurantId = rs.getInt("restaurantId");
                int userId = rs.getInt("userId");
                String ETA=rs.getString("ETA");
                o = new Order(orderId, totalAmout, modeOfPayment, status, restaurantId, userId, ETA);
                list.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, stmt);
        }

        return list;
    }

    @Override
    public boolean updateOrder(Order order) {
    	System.out.println("i am in update");
    	System.out.println(order);
        PreparedStatement pstmt = null;
        String sql = "UPDATE `order` SET totalAmout=?, modeOfPayment=?, status=?, restaurantId=?, userId=?, ETA=? WHERE orderId=? ";
        boolean success = false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, order.getTotalAmout());
            pstmt.setString(2, order.getModeOfPayment());
            pstmt.setString(3, order.getStatus());
            pstmt.setInt(4, order.getRestaurantId());
            pstmt.setInt(5, order.getUserId());
            
            pstmt.setString(6, order.getETA());
            pstmt.setString(7, order.getOrderId());

            int i = pstmt.executeUpdate();
            System.out.println("i have updted th amt");

            if (i == 1) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt);
        }

        return success;
    }

    @Override
    public boolean deleteOrder(String orderId) {
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM `order` WHERE orderId=? ";
        boolean success = false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, orderId);

            int i = pstmt.executeUpdate();
            if (i == 1) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt);
        }

        return success;
    }

    private void closeResources(ResultSet rs, Statement stmt) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void closeResources(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
