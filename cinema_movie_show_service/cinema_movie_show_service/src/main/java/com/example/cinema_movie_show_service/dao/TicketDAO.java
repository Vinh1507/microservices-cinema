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
public class TicketDAO extends MovieShowServiceDAO {

    public TicketDAO() {
    	super();
    }
    public ArrayList<Ticket> searchByMovieShowSchedule(MovieShowSchedule mss) {
        String query = "SELECT *, T.id as T_Id, S.id as S_Id, R.id as R_Id, S.Position as S_Position, R.Position as R_Position \r\n"
        		+ "FROM tblticket as T\r\n"
        		+ "JOIN tblseat as S\r\n"
        		+ "ON T.SeatId = S.Id\r\n"
        		+ "JOIN tblroom as R\r\n"
        		+ "ON R.id = S.RoomId\r\n"
        		+ "WHERE MovieShowScheduleId = ?;";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, mss.getId());
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Ticket> arr = new ArrayList<Ticket>();
            while (rs.next()) {
            	Room room = new Room(rs.getInt("R_Id"), rs.getString("Type"), rs.getString("R_Position"), rs.getInt("totalRows"), rs.getInt("totalColumns"));
            	Seat seat = new Seat(rs.getInt("S_id"), rs.getString("S_Position"), room, rs.getInt("seatRow"), rs.getInt("seatColumn"));
            	boolean isAvailable = rs.getString("BillId") == null;
                Ticket t = new Ticket(rs.getInt("T_Id"), seat, mss, rs.getDouble("Price"), isAvailable);
                arr.add(t);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Float calculateTotalAmount(ArrayList<Ticket> tickets) {
    	String ids = "";
    	for(int i = 0; i < tickets.size(); i++) {
			Ticket t = tickets.get(i);
			ids += t.getId();
			if(i < tickets.size() - 1) ids += ",";
    	}
        String query = String.format("select SUM(Price) as totalAmount\r\n"
        		+ "from tblticket\r\n"
        		+ "where id in (%s)", ids);
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) { 
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Ticket> arr = new ArrayList<Ticket>();
            if (rs.next()) {
            	return rs.getFloat("totalAmount");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Boolean updateBookedTicketByBill(Bill bill) {
    	String ticketIds = "";
    	ArrayList<Ticket> bookedTicketList = bill.getTickets();
    	for(int i = 0; i < bookedTicketList.size(); i++) {
    		ticketIds += bookedTicketList.get(i).getId();
	      	if(i < bookedTicketList.size() - 1) ticketIds += ",";
    	}
        String query = String.format("UPDATE tblticket SET BillId = ? WHERE Id IN (%s)", ticketIds);
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) { 
        	preparedStatement.setInt(1, bill.getId());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Ticket> getAllBookedTicket(){
    	String query = "SELECT *, T.id as T_Id, S.id as S_Id, R.id as R_Id, S.Position as S_Position, R.Position as R_Position, M.Id as M_Id, MSS.Id as MSS_Id\r\n"
    			+ "FROM tblticket as T\r\n"
    			+ "JOIN tblseat as S\r\n"
    			+ "ON T.SeatId = S.Id\r\n"
    			+ "JOIN tblroom as R\r\n"
    			+ "ON R.id = S.RoomId\r\n"
    			+ "JOIN tblmovieshowschedule as MSS\r\n"
    			+ "ON T.MovieShowScheduleId = MSS.Id\r\n"
    			+ "JOIN tblmovie as M\r\n"
    			+ "ON MSS.MovieId = M.Id\r\n"
    			+ "WHERE BillId is not null;";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Ticket> arr = new ArrayList<Ticket>();
            while (rs.next()) {
            	Room room = new Room(rs.getInt("R_Id"), rs.getString("Type"), rs.getString("R_Position"), rs.getInt("totalRows"), rs.getInt("totalColumns"));
            	Seat seat = new Seat(rs.getInt("S_id"), rs.getString("S_Position"), room, rs.getInt("seatRow"), rs.getInt("seatColumn"));
            	Movie movie = new Movie(rs.getInt("M_Id"), rs.getString("Title"), rs.getString("Description"));
            	MovieShowSchedule mss = new MovieShowSchedule(rs.getInt("MSS_Id"), room, null, movie);
                Ticket t = new Ticket(rs.getInt("T_Id"), seat, mss, rs.getDouble("Price"), false);
                Bill bill = new Bill();
                bill.setId(rs.getInt("BillId"));
                t.setBill(bill);
                arr.add(t);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Ticket> getBookedTicketListByMovie(Movie movie){
    	String query = "SELECT *, Sche.id as Sche_Id, T.id as T_Id, S.id as S_Id, R.id as R_Id, S.Position as S_Position, R.Position as R_Position, M.Id as M_Id, MSS.Id as MSS_Id\r\n"
    			+ "FROM tblticket as T\r\n"
    			+ "JOIN tblseat as S\r\n"
    			+ "ON T.SeatId = S.Id\r\n"
    			+ "JOIN tblroom as R\r\n"
    			+ "ON R.id = S.RoomId\r\n"
    			+ "JOIN tblmovieshowschedule as MSS\r\n"
    			+ "ON T.MovieShowScheduleId = MSS.Id\r\n"
    			+ "JOIN tblmovie as M\r\n"
    			+ "ON MSS.MovieId = M.Id\r\n"
    			+ "JOIN tblschedule as Sche\r\n"
    			+ "ON Sche.Id = MSS.ScheduleId\r\n"
    			+ "WHERE BillId is not null AND M.Id = ?;\r\n";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
        	preparedStatement.setInt(1, movie.getId());
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Ticket> arr = new ArrayList<Ticket>();
            while (rs.next()) {
            	Room room = new Room(rs.getInt("R_Id"), rs.getString("Type"), rs.getString("R_Position"), rs.getInt("totalRows"), rs.getInt("totalColumns"));
            	Seat seat = new Seat(rs.getInt("S_id"), rs.getString("S_Position"), room, rs.getInt("seatRow"), rs.getInt("seatColumn"));
            	Schedule schedule = new Schedule(rs.getInt("Sche_Id"), rs.getString("StartTime"), rs.getString("EndTime"));
            	MovieShowSchedule mss = new MovieShowSchedule(rs.getInt("MSS_Id"), room, schedule, movie);
                Ticket t = new Ticket(rs.getInt("T_Id"), seat, mss, rs.getDouble("Price"), false);
                Bill bill = new Bill();
                bill.setId(rs.getInt("BillId"));
                t.setBill(bill);
                arr.add(t);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public ArrayList<Ticket> getBookedTicketListByMovieShowSchedule(MovieShowSchedule movieShowSchedule){
    	String query = "SELECT *\r\n"
    			+ "FROM tblticket as T\r\n"
    			+ "WHERE BillId is not null AND T.MovieShowScheduleId = ?;";
        try (PreparedStatement preparedStatement = MovieShowServiceDAO.getConnection().prepareStatement(query)) {
        	preparedStatement.setInt(1, movieShowSchedule.getId());
            ResultSet rs = preparedStatement.executeQuery();
            ArrayList<Ticket> arr = new ArrayList<Ticket>();
            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getInt("Id"));
                Bill bill = new Bill();
                bill.setId(rs.getInt("BillId"));
                t.setPrice(rs.getDouble("Price"));
                t.setBill(bill);
                arr.add(t);
            }
            return arr;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
