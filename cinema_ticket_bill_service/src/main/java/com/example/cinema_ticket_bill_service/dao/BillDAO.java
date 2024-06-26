package com.example.cinema_ticket_bill_service.dao;


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
public class BillDAO extends BillServiceDAO {

    public BillDAO() {
    	super();
    }
    public Bill createBill(Bill bill) {
        String sql = "INSERT INTO tblbill (CustomerId, bookingTime, PaymentId) VALUES (?, now(), ?)";

        try (PreparedStatement preparedStatement = BillServiceDAO.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, bill.getCustomer().getId());
            preparedStatement.setInt(2, bill.getPayment().getId());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int createdId = generatedKeys.getInt(1);
                    bill.setId(createdId);
                    return bill;
                } else {
                    throw new SQLException("Insertion failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            return null;
        }
    }
    
    public ArrayList<Bill> getAllBill(String start, String end){
    	String query = "select * from tblbill\r\n"
    			+ "where bookingTime >= ? AND bookingTime < DATE_ADD(?, INTERVAL 1 DAY) ";
        try (PreparedStatement preparedStatement = BillServiceDAO.getConnection().prepareStatement(query)) {
        	preparedStatement.setString(1, start);
            preparedStatement.setString(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Bill> arr = new ArrayList<Bill>();
            while (rs.next()) {
//            	Bill bill = new Bill(rs.getInt("Id"), null, null, rs.getInt("Quantity"), rs.getFloat("TotalAmount"), rs.getString("bookingTime"), null);
            	Bill bill = new Bill();
            	bill.setId(rs.getInt("Id"));
            	bill.setBookingTime(rs.getString("bookingTime"));
                arr.add(bill);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Bill> getBillListByIds(ArrayList<Integer> billIds, String start, String end){
    	String idsString = "";
    	for(int i = 0; i < billIds.size(); i++) {
    		idsString += billIds.get(i);
    		if(i < billIds.size()-1) idsString += ",";
    	}
    	String query = "select * from tblbill\r\n"
    			+ "where bookingTime >= ? AND bookingTime < DATE_ADD(?, INTERVAL 1 DAY) AND Id IN (%s)";
    	query = String.format(query, idsString);
        try (PreparedStatement preparedStatement = BillServiceDAO.getConnection().prepareStatement(query)) {
        	preparedStatement.setString(1, start);
            preparedStatement.setString(2, end);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Bill> arr = new ArrayList<Bill>();
            while (rs.next()) {
            	Customer cust = new Customer(rs.getInt("CustomerId"), null, null, null, 0); 
//            	Bill bill = new Bill(rs.getInt("Id"), cust, null, rs.getInt("Quantity"), rs.getFloat("TotalAmount"), rs.getString("bookingTime"), null);
            	Bill bill = new Bill();
            	bill.setId(rs.getInt("Id"));
            	bill.setCustomer(cust);
            	bill.setBookingTime(rs.getString("bookingTime"));
                arr.add(bill);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
