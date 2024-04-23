package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderHistoryDao;
import com.tap.model.OrderHistory;

public class OrderHistoryDaoImpl implements OrderHistoryDao {

    static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    static private final String USERNAME = "root";
    static private final String PASSWORD = "Mr@840420";
    Connection conn = null;

    public OrderHistoryDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            handleException(e);
        }
    }

    @Override
    public boolean addOrderHistory(OrderHistory orderHistory) {
        boolean success = false;
        String sql = "INSERT INTO orderhistory(orderId, userId) VALUES(?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());

            int i = pstmt.executeUpdate();
            success = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }

        return success;
    }

    @Override
    public OrderHistory getOrderHistory(int orderHistoryId) {
        String sql = "SELECT * FROM orderhistory WHERE orderHistoryId=?";
        OrderHistory orderHistory = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderHistoryId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    orderHistory = extractOrderHistoryFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return orderHistory;
    }

    @Override
    public List<OrderHistory> getOrderHistoryByUser(int userId) {
        String sql = "SELECT * FROM orderhistory WHERE userId=?";
        List<OrderHistory> list = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderHistory orderHistory = extractOrderHistoryFromResultSet(rs);
                    list.add(orderHistory);
                }
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return list;
    }

    @Override
    public List<OrderHistory> getAllOrderHistory() {
        String sql = "SELECT * FROM orderhistory";
        List<OrderHistory> list = new ArrayList<>();

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                OrderHistory orderHistory = extractOrderHistoryFromResultSet(rs);
                list.add(orderHistory);
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return list;
    }

    @Override
    public boolean updateOrderHistory(OrderHistory orderHistory) {
        boolean success = false;
        String sql = "UPDATE orderhistory SET orderId=?, userId=? WHERE orderHistoryId=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, orderHistory.getOrderId());
            pstmt.setInt(2, orderHistory.getUserId());
            pstmt.setInt(3, orderHistory.getOrderHistoryId());

            int i = pstmt.executeUpdate();
            success = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }

        return success;
    }

    @Override
    public boolean deleteOrderHistory(int orderHistoryId) {
        boolean success = false;
        String sql = "DELETE FROM orderhistory WHERE orderHistoryId=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderHistoryId);

            int i = pstmt.executeUpdate();
            success = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }

        return success;
    }

    private OrderHistory extractOrderHistoryFromResultSet(ResultSet rs) throws SQLException {
        int orderHistoryId = rs.getInt("orderHistoryId");
        String orderId = rs.getString("orderId");
        int userId = rs.getInt("userId");
        return new OrderHistory(orderHistoryId, orderId, userId);
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
