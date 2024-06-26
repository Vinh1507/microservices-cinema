package com.example.cinema_user_service.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.*;

@Repository
public class UserDAO extends UserServiceDAO {

    public UserDAO() {
    	super();
    }
    
    public User login(User user) {
        String query = "SELECT *, U.Id as U_Id\r\n"
        		+ "FROM tbluser as U\r\n"
        		+ "JOIN tblfullname as F\r\n"
        		+ "ON U.FullNameId = F.Id\r\n"
        		+ "WHERE username = ?\r\n"
        		+ "AND password = ?";
        try (PreparedStatement preparedStatement = UserServiceDAO.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
        		Fullname fullname = new Fullname(rs.getString("FirstName"), rs.getString("LastName"));
        		user = new User(rs.getInt("U_Id"), fullname, rs.getString("Username"));
        		user.setRole(rs.getString("Discriminator"));
        		return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public User findById(int id) {
        String query = "SELECT *, U.Id as U_Id\r\n"
        		+ "FROM tbluser as U\r\n"
        		+ "JOIN tblfullname as F\r\n"
        		+ "ON U.FullNameId = F.Id\r\n"
        		+ "WHERE U.Id = ?;";
        try (PreparedStatement preparedStatement = UserServiceDAO.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
        		Fullname fullname = new Fullname(rs.getString("FirstName"), rs.getString("LastName"));
        		User user = new User(rs.getInt("U_Id"), fullname, rs.getString("Username"));
        		return user;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<User> getUserListByIds(ArrayList<Integer> userIds) {
    	String ids = "";
    	for(int i = 0; i < userIds.size(); i++) {
    		ids += userIds.get(i);
    		if(i < userIds.size()-1) ids += ",";
    	}
    	System.out.println("IDS" + ids);
        String query = "SELECT *, U.Id as U_Id\r\n"
        		+ "FROM tbluser as U\r\n"
        		+ "JOIN tblfullname as F\r\n"
        		+ "ON U.FullNameId = F.Id\r\n"
        		+ "WHERE U.Id IN (%s);";
        query = String.format(query, ids);
        try (PreparedStatement preparedStatement = UserServiceDAO.getConnection().prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<User> arr = new ArrayList<User>();
            while (rs.next()) {
        		Fullname fullname = new Fullname(rs.getString("FirstName"), rs.getString("LastName"));
        		User user = new User(rs.getInt("U_Id"), fullname, rs.getString("Username"));
        		arr.add(user);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
