package com.example.cinema_importing_bill_service.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ImportingBillServiceDAO {
	public static Connection con;
    public ImportingBillServiceDAO() {
    	
    }
    
    public static Connection getConnection() {
    	if(con == null){
            String jdbcURL = "jdbc:mysql://192.168.67.2:32008/cinema_importing_bill_db";
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
