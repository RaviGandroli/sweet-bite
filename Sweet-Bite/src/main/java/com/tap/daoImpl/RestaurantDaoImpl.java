package com.tap.daoImpl;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.tap.dao.RestaurantDao;
import com.tap.model.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao {

    static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    static private final String USERNAME = "root";
    static private final String PASSWORD = "Mr@840420";
    Connection conn = null;

    public RestaurantDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            handleException(e);
        }
    }

    @Override
    public boolean addRestaurant(Restaurant restaurant) {
        boolean f = false;
        String sql = "insert into restaurant(`restaurantId`, `restaurantName`, `restaurantImagePath`, `ratings`, `eta`, `cuisineType`, `address`, `isActive`, `restaurantOwnerId`) values(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, restaurant.getRestaurantId());
            pstmt.setString(2, restaurant.getRestaurantName());
            pstmt.setString(3, restaurant.getRestaurantImagePath());
            pstmt.setFloat(4, restaurant.getRatings());
            pstmt.setString(5, restaurant.getEta());
            pstmt.setString(6, restaurant.getCuisineType());
            pstmt.setString(7, restaurant.getAddress());
            pstmt.setBoolean(8, restaurant.isActive());
            pstmt.setInt(9, restaurant.getRestaurantOwnerId());

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
    public Restaurant getRestaurant(int restaurantId) {
        String sql = "select * from restaurant where restaurantId=?";
        Restaurant r = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                r = extractRestaurantFromResultSet(rs);
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return r;
    }
    
    public List<Restaurant> getRestaurantBySearch(String  ch) {
		List<Restaurant> list = new ArrayList<Restaurant>();
 System.out.println(" i am in dao");
        String sql = "select * from restaurant where restaurantName like ? or cuisineType like ? or ratings like ? or address=? ";
        Restaurant r = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        	pstmt.setString(1, "%" + ch + "%");
			pstmt.setString(2, "%" + ch + "%");
			pstmt.setString(3, "%" + ch + "%");
			pstmt.setString(4, "%" + ch + "%");        
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                r = extractRestaurantFromResultSet(rs);
                list.add(r);
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return list;
    }

    public Restaurant getRestaurantByOwner(int restaurantOwnerId) {
        String sql = "select * from restaurant where restaurantOwnerId=?";
        Restaurant r = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, restaurantOwnerId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                r = extractRestaurantFromResultSet(rs);
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return r;
    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        String sql = "select * from restaurant ";
        ArrayList<Restaurant> list = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Restaurant r = extractRestaurantFromResultSet(rs);
                list.add(r);
            }
        } catch (SQLException e) {
            handleException(e);
        }

        return list;
    }

    @Override
    public boolean updateRestaurant(Restaurant restaurant) {
        String sql = "update restaurant set restaurantName=?,restaurantImagePath=?,ratings=?,eta=?,cuisineType=?,address=?,isActive=?,restaurantOwnerId=? where restaurantId=? ";
        boolean f = false;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, restaurant.getRestaurantName());
            pstmt.setString(2, restaurant.getRestaurantImagePath());
            pstmt.setFloat(3, restaurant.getRatings());
            pstmt.setString(4, restaurant.getEta());
            pstmt.setString(5, restaurant.getCuisineType());
            pstmt.setString(6, restaurant.getAddress());
            pstmt.setBoolean(7, restaurant.isActive());
            pstmt.setInt(8, restaurant.getRestaurantOwnerId());
            pstmt.setInt(9, restaurant.getRestaurantId());

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
    public boolean deleteRestaurant(int restaurantId) {
        String sql = "delete from restaurant where restaurantId=? ";
        boolean f = false;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, restaurantId);
            int i = pstmt.executeUpdate();
            f = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }

        return f;
    }

    private Restaurant extractRestaurantFromResultSet(ResultSet rs) throws SQLException {
        int restaurantId = rs.getInt("restaurantId");
        String restaurantName = rs.getString("restaurantName");
        String restaurantImagePath = rs.getString("restaurantImagePath");
        float ratings = rs.getFloat("ratings");
        String eta = rs.getString("eta");
        String cuisineType = rs.getString("cuisineType");
        String address = rs.getString("address");
        boolean isActive = rs.getBoolean("isActive");
        int restaurantOwnerId = rs.getInt("restaurantOwnerId");
        return new Restaurant(restaurantId, restaurantName, restaurantImagePath, ratings, eta, cuisineType, address, isActive, restaurantOwnerId);
    }

    private void handleException(Exception e) {
        e.printStackTrace();
        // Log the exception or perform any other necessary actions.
    }

    public void closeResources() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                handleException(e);
            }
        }
    }
}
