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
public class MovieShowScheduleDAO extends MovieShowServiceDAO {

    public MovieShowScheduleDAO() {
    	super();
    }
    
    public ArrayList<MovieShowSchedule> searchMovieScheduleByMovie(Movie movie) {
        String query = "select *, MSS.Id as MSS_Id, R.id as R_Id, S.id as S_Id\r\n"
        		+ "from tblmovieshowschedule as MSS\r\n"
        		+ "join tblroom as R\r\n"
        		+ "on MSS.RoomId = R.Id\r\n"
        		+ "JOIN tblschedule as S\r\n"
        		+ "on MSS.ScheduleId = S.Id\r\n"
        		+ "where MSS.MovieId = ? AND MSS.id IN (SELECT MovieShowScheduleId FROM tblticket) AND S.StartTime > now(); ";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, movie.getId());
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<MovieShowSchedule> arr = new ArrayList<MovieShowSchedule>();
            while (rs.next()) {
            	Room room = new Room(rs.getInt("R_Id"), rs.getString("Type"), rs.getString("Position"));
            	Schedule schedule = new Schedule(rs.getInt("S_Id"), rs.getString("StartTime"), rs.getString("EndTime"));
            	MovieShowSchedule mss = new MovieShowSchedule(rs.getInt("MSS_Id"), room, schedule, movie);
            	arr.add(mss);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public MovieShowSchedule getMovieShowScheduleById(int id) {
    	Movie movie = new MovieDAO().getMovieByMovieScheduleId(id);
        String query = "select *, MSS.Id as MSS_Id, R.id as R_Id, S.id as S_Id\r\n"
        		+ "from tblmovieshowschedule as MSS\r\n"
        		+ "join tblroom as R\r\n"
        		+ "on MSS.RoomId = R.Id\r\n"
        		+ "JOIN tblschedule as S\r\n"
        		+ "on MSS.ScheduleId = S.Id\r\n"
        		+ "where MSS.Id = ?";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<MovieShowSchedule> arr = new ArrayList<MovieShowSchedule>();
            if (rs.next()) {
            	Room room = new Room(rs.getInt("R_Id"), rs.getString("Type"), rs.getString("Position"));
            	Schedule schedule = new Schedule(rs.getInt("S_Id"), rs.getString("StartTime"), rs.getString("EndTime"));
            	MovieShowSchedule mss = new MovieShowSchedule(rs.getInt("MSS_Id"), room, schedule, movie);
            	return mss;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<MovieShowScheduleStat> getMovieShowScheduleStat(Movie movie, int[] billIds) {
    	String billIdStr = "";
    	for(int i = 0; i < billIds.length; i++) {
    		billIdStr += billIds[i];
    		System.out.println("BILL: " + billIds[i]);
    		if(i < billIds.length-1) {
    			billIdStr += ",";
    		}
    	}
    	billIdStr = "(" + billIdStr + ")";
    	System.out.println("BILL: " + billIdStr);
        String query = String.format("select MSS.*, S.*, R.*, R.Id as R_Id, S.Id as S_Id, MSS.Id as MSS_Id, sum(T.Price) as revenue, count(*) as total_tickets\r\n"
        		+ "from tblmovieshowschedule as MSS\r\n"
        		+ "join tblroom as R\r\n"
        		+ "on MSS.RoomId = R.Id\r\n"
        		+ "join tblschedule as S\r\n"
        		+ "on MSS.ScheduleId = S.Id\r\n"
        		+ "join tblticket as T\r\n"
        		+ "on T.MovieShowScheduleId = MSS.Id\r\n"
        		+ "where T.BillId IN %s\r\n"
        		+ "and MSS.MovieId = %d\r\n"
        		+ "group by MSS.Id\r\n"
        		+ "having total_tickets > 0", billIdStr, movie.getId());
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<MovieShowScheduleStat> arr = new ArrayList<>();
            while (rs.next()) {
            	Room room = new Room(rs.getInt("R_Id"), rs.getString("Type"), rs.getString("Position"));
            	Schedule schedule = new Schedule(rs.getInt("S_Id"), rs.getString("StartTime"), rs.getString("EndTime"));
            	MovieShowSchedule mss = new MovieShowSchedule(rs.getInt("MSS_Id"), room, schedule, movie);
            	MovieShowScheduleStat stat = new MovieShowScheduleStat();
            	stat.setMovieShowSchedule(mss);
            	stat.setRevenue(rs.getFloat("revenue"));
            	stat.setTotalTickets(rs.getInt("total_tickets"));
            	arr.add(stat);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<MovieShowScheduleStat> arr = new ArrayList<>();
            return arr;
        }
    }
}
