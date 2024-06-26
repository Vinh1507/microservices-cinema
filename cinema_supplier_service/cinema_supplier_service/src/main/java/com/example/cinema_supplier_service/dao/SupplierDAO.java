package com.example.cinema_supplier_service.dao;

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
public class SupplierDAO extends SupplierServiceDAO {

    public SupplierDAO() {
    	super();
    }
    
    public ArrayList<Supplier> getSupplierList() {
        String query = "select * from tblsupplier";
        try (PreparedStatement preparedStatement = this.con.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Supplier> arr = new ArrayList<Supplier>();
            while (rs.next()) {
            	arr.add(new Supplier(rs.getInt("id"), rs.getString("Email"), rs.getString("Contact")));
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
