package com.example.cinema_movie_show_service.dao;


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
public class MovieDetailDAO extends MovieShowServiceDAO {

	public MovieDetailDAO() {
		super();
	}
	
	public Boolean createMovieDetail(ImportingBill bill) {
    	ArrayList<MovieDetail> importedMovieDetails = bill.getMovieDetails();
    	for(MovieDetail movieDetail : importedMovieDetails) {
    		String sql = "insert into tblmoviedetail(MovieId, ImportPrice, ImportingBillId)\r\n"
        			+ "values (?, ?, ?)";
	        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(sql)) { 
	        		preparedStatement.setInt(1, movieDetail.getMovie().getId());
	                preparedStatement.setFloat(2, movieDetail.getImportingPrice());
	                preparedStatement.setFloat(3, bill.getId());
	                preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
    	}
    	return true;
    }
    
}
