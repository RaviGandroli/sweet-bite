package com.tap.daoImpl;

import java.security.MessageDigest;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.tap.dao.UserDao;
import com.tap.model.User;

public class UserDaoImpl implements UserDao {

    static private final String URL = "jdbc:mysql://localhost:3306/tapfoods";
    static private final String USERNAME = "root";
    static private final String PASSWORD = "Mr@840420";
    Connection conn = null;

    public UserDaoImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            handleException(e);
        }
    }

    @Override
    public boolean addUser(User user) {
        String sql;
        if (user.getRole() == null) {
            sql = "INSERT INTO user(`name`, `email`, `phoneNo`, `address`, `userName`, `password`) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO user(`name`, `email`, `phoneNo`, `address`, `userName`, `password`, `role`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        }
        boolean f = false;
        String userName = encryption(user.getUserName());
        String password = encryption(user.getPassword());
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setLong(3, user.getPhoneNo());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, userName);
            pstmt.setString(6, password);
            if (user.getRole() != null) {
                pstmt.setString(7, user.getRole());
            }

            int i = pstmt.executeUpdate();
            f = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }
        return f;
    }

//    static String encryption(String str) {
//        StringBuilder encryptedString = new StringBuilder();
//        for (char c : str.toCharArray()) {
//            encryptedString.append((char) (c + 1));
//        }
//        return encryptedString.toString();
//    }
//
//    static String decryption(String str) {
//        StringBuilder decryptedString = new StringBuilder();
//        for (char c : str.toCharArray()) {
//            decryptedString.append((char) (c - 1));
//        }
//        return decryptedString.toString();
//    }
    
    
    
    
    //encryption and decryption 
    
    private static final String passphrase = "IndianFoodTaste";

    public static String encryption(String strToEncrypt) {
        try {
            byte[] key = generateKey(passphrase);

            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(strToEncrypt.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decryption(String strToDecrypt) {
        try {
            byte[] key = generateKey(passphrase);

            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
            return new String(decryptedBytes);
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    private static byte[] generateKey(String passphrase) throws Exception {
        // Use a key derivation function like PBKDF2 for more secure key derivation
        return Arrays.copyOf(MessageDigest.getInstance("SHA-256").digest(passphrase.getBytes("UTF-8")), 16);
    }

    
    
    
    
    
    
    
    
    
    
    
    public User checkLogin(String userName, String password) {
        String sql = "SELECT * FROM user WHERE userName=? AND password=?";
        User user = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, encryption(userName));
            pstmt.setString(2, encryption(password));
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("userId");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    long phoneNo = rs.getLong("phoneNo");
                    String address = rs.getString("address");
                    String userNameDecrypted = decryption(rs.getString("userName"));
                    String passwordDecrypted = decryption(rs.getString("password"));
                    String role = rs.getString("role");

                    user = new User(userId, name, email, phoneNo, address, userNameDecrypted, passwordDecrypted, role);
                }
            }
        } catch (SQLException e) {
            handleException(e);
        }
        return user;
    }

    @Override
    public User getUser(int userId) {
        String sql = "SELECT * FROM user WHERE userId=?";
        User user = null;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int Id = rs.getInt("userId");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    long phoneNo = rs.getLong("phoneNo");
                    String address = rs.getString("address");
                    String userName = decryption(rs.getString("userName"));
                    String password = decryption(rs.getString("password"));
                    String role = rs.getString("role");
                    user = new User(Id, name, email, phoneNo, address, userName, password, role);
                }
            }
        } catch (SQLException e) {
            handleException(e);
        }
        return user;
    }

    @Override
    public List<User> getAllUser() {
        String sql = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int userId = rs.getInt("userId");
                String name = rs.getString("name");
                String email = rs.getString("email");
                Long phoneNo = rs.getLong("phoneNo");
                String address = rs.getString("address");
                String userName = decryption(rs.getString("userName"));
                String password = decryption(rs.getString("password"));
                String role = rs.getString("role");
                User user = new User(userId, name, email, phoneNo, address, userName, password, role);
                userList.add(user);
            }
        } catch (SQLException e) {
            handleException(e);
        }
        return userList;
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET name=?, email=?, phoneNo=?, address=?, userName=?, password=?, role=? WHERE userId=?";
        boolean f = false;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setLong(3, user.getPhoneNo());
            pstmt.setString(4, user.getAddress());
            pstmt.setString(5, encryption(user.getUserName()));
            pstmt.setString(6, encryption(user.getPassword()));
            pstmt.setString(7, user.getRole());
            pstmt.setInt(8, user.getUserId());
            int i = pstmt.executeUpdate();
            f = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }
        return f;
    }

    @Override
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM user WHERE userId=?";
        boolean f = false;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int i = pstmt.executeUpdate();
            f = (i == 1);
        } catch (SQLException e) {
            handleException(e);
        }
        return f;
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
