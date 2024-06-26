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
public class PaymentDAO extends BillServiceDAO {

    public PaymentDAO() {
    	super();
    }
    public Payment createPayment(Payment payment) {
        String sql = "INSERT INTO tblpayment (PaymentMethod) VALUES (?)";

        try (PreparedStatement preparedStatement = BillServiceDAO.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, payment.getPaymentMethod());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int createdId = generatedKeys.getInt(1);
                    payment.setId(createdId);
                    return payment;
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
