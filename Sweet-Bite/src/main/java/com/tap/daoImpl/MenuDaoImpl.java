package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.MenuDao;
import com.tap.model.Menu;

public class MenuDaoImpl implements MenuDao {

    static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    static private final String USERNAME = "root";
    static private final String PASSWORD = "Mr@840420";
    Connection conn = null;

    public MenuDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addMenu(Menu menu) {
        boolean success = false;
        String sql = "insert into menu(menuName, price, description, isAvailable, restaurantId, ratings, imagePath) values(?,?,?,?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, menu.getMenuName());
            pstmt.setDouble(2, menu.getPrice());
            pstmt.setString(3, menu.getDescription());
            pstmt.setBoolean(4, menu.isAvailable());
            pstmt.setInt(5, menu.getRestaurantId());
            pstmt.setFloat(6, menu.getRatings());
            pstmt.setString(7, menu.getImagepath());

            int i = pstmt.executeUpdate();
            success = (i == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt);
        }

        return success;
    }

    @Override
    public Menu getMenu(int menuId) {
        String sql = "select * from menu where menuId=?";
        Menu m = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, menuId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String menuName = rs.getString("menuName");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String imagePath = rs.getString("imagePath");
                boolean isAvailable = rs.getBoolean("isAvailable");
                int restaurantId = rs.getInt("restaurantId");
                float ratings = rs.getFloat("ratings");

                m = new Menu(menuId, menuName, price, description, imagePath, isAvailable, restaurantId, ratings);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pstmt);
        }

        return m;
    }

    @Override
    public List<Menu> getAllMenu(int restaurantId) {
        String sql = "select * from menu where restaurantId=? ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Menu> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, restaurantId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                int menuId = rs.getInt("menuId");
                String menuName = rs.getString("menuName");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String imagePath = rs.getString("imagePath");
                boolean isAvailable = rs.getBoolean("isAvailable");
                float ratings = rs.getFloat("ratings");

                Menu m = new Menu(menuId, menuName, price, description, imagePath, isAvailable, restaurantId, ratings);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pstmt);
        }

        return list;
    }

    @Override
    public boolean updateMenu(Menu menu) {
        PreparedStatement pstmt = null;
        String sql = "update menu set menuName=?,price=?,description=?,isAvailable=?,restaurantId=?,ratings=?,imagePath=? where menuId=? ";
        boolean success = false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, menu.getMenuName());
            pstmt.setDouble(2, menu.getPrice());
            pstmt.setString(3, menu.getDescription());
            pstmt.setBoolean(4, menu.isAvailable());
            pstmt.setInt(5, menu.getRestaurantId());
            pstmt.setFloat(6, menu.getRatings());
            pstmt.setString(7, menu.getImagepath());
            pstmt.setInt(8, menu.getMenuId());

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

    public List<Menu> getAllMenuBySearch(String  ch) {
      
        String sql = "select * from menu where menuName like ? or price like ? or ratings like ? and description=? ";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Menu> list = new ArrayList<>();

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + ch + "%");
			pstmt.setString(2, "%" + ch + "%");
			pstmt.setString(3, "%" + ch + "%");
			pstmt.setString(4, "%" + ch + "%");        
			 rs = pstmt.executeQuery();
            
            while (rs.next()) {
                int menuId = rs.getInt("menuId");
                String menuName = rs.getString("menuName");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                String imagePath = rs.getString("imagePath");
                boolean isAvailable = rs.getBoolean("isAvailable");
                int restaurantId=rs.getInt("restaurantId");
                float ratings = rs.getFloat("ratings");

                Menu m = new Menu(menuId, menuName, price, description, imagePath, isAvailable, restaurantId, ratings);
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pstmt);
        }

        return list;
    }
    
    @Override
    public boolean deleteMenu(int menuId) {
        PreparedStatement pstmt = null;
        String sql = "delete from menu where menuId=? ";
        boolean success = false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, menuId);

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

    @Override
    public boolean deleteMenuByRes(int restaurantId) {
        PreparedStatement pstmt = null;
        String sql = "delete from menu where restaurantId=? ";
        boolean success = false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, restaurantId);

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

    @Override
    public boolean deleteMenuByResAndName(int restaurantId, String menuName) {
        PreparedStatement pstmt = null;
        String sql = "delete from menu where restaurantId=? and menuName=? ";
        boolean success = false;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, restaurantId);
            pstmt.setString(2, menuName);

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

    private void closeResources(ResultSet rs, PreparedStatement pstmt) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
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
