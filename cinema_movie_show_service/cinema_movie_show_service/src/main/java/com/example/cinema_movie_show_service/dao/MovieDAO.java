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
public class MovieDAO extends MovieShowServiceDAO {

	public MovieDAO() {
		super();
	}
	
    public ArrayList<Movie> getMovieList(String key) {
    	if(key == null) key = "";
        String query = "select * from tblmovie\r\n"
        		+ "where Title LIKE ?\r\n"
        		+ "AND Id in (select distinct MovieId\r\n"
        		+ "from tblmovieshowschedule as MSS\r\n"
        		+ "join tblticket as T\r\n"
        		+ "on MSS.Id = T.MovieShowScheduleId);\r\n";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
        	preparedStatement.setString(1, "%" + key + "%");
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Movie> arr = new ArrayList<Movie>();
            while (rs.next()) {
            	Movie m = new Movie(rs.getInt("Id"), rs.getString("Title"), rs.getString("Category"), rs.getString("Description"));
            	arr.add(m);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Movie getMovieById(int id) {
        String query = "select * from tblmovie where Id = ?;";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
        	preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	Movie m = new Movie(rs.getInt("Id"), rs.getString("Title"), rs.getString("Category"), rs.getString("Description"));
            	return m;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Movie getMovieByMovieScheduleId(int mssId) {
        String query = "select *, M.Id as M_Id \r\n"
        		+ "from tblmovie as M, tblmovieshowschedule as MSS\r\n"
        		+ "where M.Id = MSS.MovieId\r\n"
        		+ "and MSS.Id = ?";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
        	preparedStatement.setInt(1, mssId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
            	Movie m = new Movie(rs.getInt("M_Id"), rs.getString("Title"), rs.getString("Category"), rs.getString("Description"));
            	return m;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
   
    public ArrayList<Movie> findMovieListToImport() {
        String query = "select * from tblmovie";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Movie> arr = new ArrayList<Movie>();
            while (rs.next()) {
            	Movie m = new Movie(rs.getInt("Id"), rs.getString("Title"), rs.getString("Category"), rs.getString("Description"), rs.getFloat("Price"));
            	arr.add(m);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<MovieStat> getMovieStat(List<Bill> billList){
    	String billIds = "";
    	for(int i = 0; i < billList.size(); i++) {
    		billIds += billList.get(i).getId();
    		if(i < billList.size()-1) {
    			billIds += ",";
    		}
    	}
    	billIds = "(" + billIds + ")";
    	String query = String.format("select M.*, sum(T.Price) as revenue, count(*) as total_tickets\r\n"
    			+ "from tblmovie as M\r\n"
    			+ "join tblmovieshowschedule as MSS\r\n"
    			+ "on M.Id = MSS.MovieId\r\n"
    			+ "join tblticket as T\r\n"
    			+ "on T.MovieShowScheduleId = MSS.Id\r\n"
    			+ "where T.BillId IN %s\r\n"
    			+ "group by M.Id\r\n"
    			+ "having total_tickets > 0\r\n"
    			+ "order by revenue desc;", billIds);
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<MovieStat> arr = new ArrayList<>();
            while (rs.next()) {
            	Movie m = new Movie(rs.getInt("Id"), rs.getString("Title"), rs.getString("Category"), rs.getString("Description"), rs.getFloat("Price"));
            	MovieStat movieStat = new MovieStat();
            	movieStat.setMovie(m);
            	movieStat.setRevenue(rs.getFloat("revenue"));
            	movieStat.setTotalTickets(rs.getInt("total_tickets"));
            	arr.add(movieStat);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<MovieStat> arr = new ArrayList<>();
            return arr;
        }
    }
}
