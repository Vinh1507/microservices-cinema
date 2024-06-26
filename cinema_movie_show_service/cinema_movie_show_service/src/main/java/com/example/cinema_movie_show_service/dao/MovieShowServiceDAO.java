package com.example.cinema_movie_show_service.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class MovieShowServiceDAO {
	private static Connection con;
    public MovieShowServiceDAO() {
        
    }
    
    public static Connection getConnection() {
    	if(con == null){
            String jdbcURL = "jdbc:mysql://192.168.67.2:32002/cinema_movie_show_db";
            String jdbcUserName = "root";
            String jdbcPassWord = "123456789";
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassWord);
                System.out.print("Connect DB Successfully!");
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    	return con;
    }
}
