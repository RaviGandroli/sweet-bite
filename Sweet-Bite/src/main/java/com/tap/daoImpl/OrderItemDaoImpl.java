package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.OrderItemDao;
import com.tap.model.OrderItem;

public class OrderItemDaoImpl implements OrderItemDao {

    static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    static private final String USERNAME = "root";
    static private final String PASSWORD = "Mr@840420";
    Connection conn = null;

    public OrderItemDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            handleException(e);
        }
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) {
        boolean success = false;
        String sql = "INSERT INTO orderitem (menuId, userId, itemName, ratings, quantity, price,orderId) VALUES (?, ?, ?, ?, ?, ?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getMenuId());
            pstmt.setInt(2, orderItem.getUserId());
            pstmt.setString(3, orderItem.getItemName());
            pstmt.setFloat(4, orderItem.getRatings());
            pstmt.setInt(5, orderItem.getQuantity());
            pstmt.setDouble(6, orderItem.getPrice());
            pstmt.setString(7, orderItem.getOrderId());

            int i = pstmt.executeUpdate();
            success = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }
        

        return success;
    }

    @Override
    public OrderItem getOrderItem(int orderItemId) {
        String sql = "SELECT * FROM orderitem WHERE orderItemId=?";
        OrderItem orderItem = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItemId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    orderItem = extractOrderItemFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return orderItem;
    }

    @Override
    public List<OrderItem> getOrderItemByUser(int userId) {
        String sql = "SELECT * FROM orderitem WHERE userId=?";
        List<OrderItem> orderItemList = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OrderItem orderItem = extractOrderItemFromResultSet(rs);
                    orderItemList.add(orderItem);
                }
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return orderItemList;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
    	System.out.println("i am in getall");
        String sql = "SELECT * FROM orderitem";
        List<OrderItem> orderItemList = new ArrayList<>();

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                OrderItem orderItem = extractOrderItemFromResultSet(rs);
                orderItemList.add(orderItem);
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return orderItemList;
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        boolean success = false;
        String sql = "UPDATE orderitem SET menuId=?, userId=?, itemName=?, ratings=?, quantity=?, price=?,orderId=? WHERE orderItemId=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItem.getMenuId());
            pstmt.setInt(2, orderItem.getUserId());
            pstmt.setString(3, orderItem.getItemName());
            pstmt.setFloat(4, orderItem.getRatings());
            pstmt.setInt(5, orderItem.getQuantity());
            pstmt.setDouble(6, orderItem.getPrice());
            pstmt.setString(7, orderItem.getOrderId());
            pstmt.setInt(8, orderItem.getOrderItemId());
          

            int i = pstmt.executeUpdate();
            success = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }

        return success;
    }

    @Override
    public boolean deleteOrderItem(int orderItemId) {
        boolean success = false;
        String sql = "DELETE FROM orderitem WHERE orderItemId=?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderItemId);

            int i = pstmt.executeUpdate();
            success = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }

        return success;
    }

    private OrderItem extractOrderItemFromResultSet(ResultSet rs) throws SQLException {
    	System.out.println(rs);
        int orderItemId = rs.getInt("orderItemId");
        int menuId = rs.getInt("menuId");
        int userId = rs.getInt("userId");
        String itemName = rs.getString("itemName");
        float ratings = rs.getFloat("ratings");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        String orderId=rs.getString("orderId");
        System.out.println(orderId);
        return new OrderItem(orderItemId, menuId, userId, itemName, ratings, quantity, price,orderId);
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

	public boolean deleteOrdersByRestaurant(int resId) {
		 boolean success = false;
	        String sql = "DELETE FROM orderitem WHERE restaurantId=?";

	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setInt(1, resId);

	            int i = pstmt.executeUpdate();
	            success = (i == 1);
	        } catch (SQLException e) {
	            handleException(e);
	        }

	        return success;		
	}
}
