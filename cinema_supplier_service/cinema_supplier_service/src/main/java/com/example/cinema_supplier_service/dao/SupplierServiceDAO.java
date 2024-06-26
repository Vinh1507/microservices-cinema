package com.example.cinema_supplier_service.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SupplierServiceDAO {
	protected Connection con;
    public SupplierServiceDAO() {
    	if(con == null){
            String jdbcURL = "jdbc:mysql://192.168.67.2:32006/cinema_supplier_db";
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
    }
    
}
