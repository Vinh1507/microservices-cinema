package com.example.cinema_importing_bill_service.dao;

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
public class ImportingBillDAO extends ImportingBillServiceDAO {

    public ImportingBillDAO() {
    	super();
    }
    
    public ImportingBill createImportingBill(ImportingBill bill) {
        String sql = "INSERT INTO tblimportingbill (ManagerId, SupplierId, ImportDate)\r\n"
        		+ "VALUES (?, ?, now())";

        try (PreparedStatement preparedStatement = ImportingBillServiceDAO.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, bill.getManager().getId());
            preparedStatement.setInt(2, bill.getSupplier().getId());
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
        	e.printStackTrace();
            return null;
        }
    }
}
