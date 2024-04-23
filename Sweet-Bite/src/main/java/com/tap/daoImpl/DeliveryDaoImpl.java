package com.tap.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.dao.DeliveryDao;
import com.tap.model.DeliveryItems;

public class DeliveryDaoImpl implements DeliveryDao {

    static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    static private final String USERNAME = "root";
    static private final String PASSWORD = "Mr@840420";
    Connection conn = null;

    public DeliveryDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addDeliveryOrders(DeliveryItems deliveryitems) {
    	System.out.println(deliveryitems);
        boolean success = false;
        String sql = "insert into deliverysorders(agentName, OTP, orderId,agentPassword) values(?,?,?,?)";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deliveryitems.getAgentName());
            pstmt.setInt(2, deliveryitems.getOTP());
            pstmt.setString(3, deliveryitems.getOrderId());
            pstmt.setString(4, deliveryitems.getPassword());

            int i = pstmt.executeUpdate();
            System.out.println(i);
            if(i==1) {
            	success=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(pstmt);
        }

        return success;
    }

    @Override
    public DeliveryItems getDeliveryOrders(int deliveryOrderId) {
        String sql = "select * from deliverysorders where deliveryOrderId=?";
        DeliveryItems di = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deliveryOrderId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String agentName = rs.getString("agentName");
                int OTP = rs.getInt("OTP");
                String orderId = rs.getString("orderId");
             
                String password = rs.getString("agentPassword");

                di = new DeliveryItems(agentName, OTP, orderId, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pstmt);
        }

        return di;
    }

    @Override
    public List<DeliveryItems> getAllDeliveryOrders() {
        String sql = "select * from deliverysorders";
        List<DeliveryItems> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String agentName = rs.getString("agentName");
                int OTP = rs.getInt("OTP");
                String orderId = rs.getString("orderId");
                
                String password = rs.getString("agentPassword");

                DeliveryItems di = new DeliveryItems(agentName, OTP, orderId, password);
                list.add(di);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pstmt);
        }

        return list;
    }

    @Override
    public List<DeliveryItems> getAllDeliveryOrdersByAgent(String agentName, String password) {
        String sql = "select * from deliverysorders where agentName=? and agentPassword=?";
        List<DeliveryItems> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, agentName);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                agentName = rs.getString("agentName");
                int OTP = rs.getInt("OTP");
                String orderId = rs.getString("orderId");
             
                password = rs.getString("agentPassword");

                DeliveryItems di = new DeliveryItems(agentName, OTP, orderId,  password);
                System.out.println(di);
                list.add(di);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(rs, pstmt);
        }

        return list;
    }

    @Override
    public boolean updateDeliveryOrders(DeliveryItems deliveryitems) {
        boolean success = false;
        String sql = "update deliverysorders set agentName=?, OTP=?, orderId=?, orderItemId=? where deliveryOrderId=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, deliveryitems.getAgentName());
            pstmt.setInt(2, deliveryitems.getOTP());
            pstmt.setString(3, deliveryitems.getOrderId());
          

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
    public boolean deleteDeliveryOrders(int deliveryOrderId) {
        boolean success = false;
        String sql = "delete from menu where deliveryOrderId=?";
        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, deliveryOrderId);

            int i = pstmt.executeUpdate();
            success = (i == 1);
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
