package com.example.cinema_movie_show_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_movie_show_service.dao.MovieDAO;
import com.example.cinema_movie_show_service.dao.MovieDetailDAO;
import com.example.cinema_movie_show_service.dao.MovieShowScheduleDAO;
import com.example.cinema_movie_show_service.dao.TicketDAO;

import model.Bill;
import model.ImportingBill;
import model.Movie;
import model.MovieShowSchedule;
import model.MovieShowScheduleStat;
import model.MovieStat;
import model.Room;
import model.Schedule;
import model.Ticket;


@RestController
@RequestMapping("/api")
public class MovieShowServiceController {
    @GetMapping("/get-movie-list")
    public ArrayList<Movie> getList(@RequestParam String key) {
    	ArrayList<Movie> arr = new MovieDAO().getMovieList(key);
    	return arr;
    }

    @PostMapping("/get-movie-show-schedule-by-movie")
    public ArrayList<MovieShowSchedule> getMovieShowScheduleByMovie(@RequestBody Movie movie) {
    	System.out.println("Movie: " + movie.getTitle());
    	ArrayList<MovieShowSchedule> arr = new MovieShowScheduleDAO().searchMovieScheduleByMovie(movie);
    	return arr;
    }
    
    @PostMapping("/get-ticket-list-by-movie-show-schedule")
    public ArrayList<Ticket> getTicketListByMovieShowSchedule(@RequestBody MovieShowSchedule movieShowSchedule) {
    	System.out.println("MSS: " + movieShowSchedule.getId());
    	ArrayList<Ticket> result = new TicketDAO().searchByMovieShowSchedule(movieShowSchedule);
    	return result;
    }
    
    @PostMapping("/calculate-total-amount")
    public Float calculateTotalAmountTicketIds(@RequestBody ArrayList<Ticket> tickets) {
    	Float result = new TicketDAO().calculateTotalAmount(tickets);
    	return result;
    }
    
    @PostMapping("/update-booked-ticket-by-bill")
    public Boolean updateTicketBillId(@RequestBody Bill bill) {
    	Boolean result = new TicketDAO().updateBookedTicketByBill(bill);
    	return result;
    }

    @GetMapping("/get-importing-movie-list")
    public ArrayList<Movie> getMovieListToImport() {
    	ArrayList<Movie> arr = new MovieDAO().findMovieListToImport();
    	return arr;
    }

    
    @PostMapping("/create-import-movie-detail")
    public Boolean createImportMovieDetail(@RequestBody ImportingBill bill) {
    	Boolean result = new MovieDetailDAO().createMovieDetail(bill);
    	return result;
    }
    
    @PostMapping("/get-all-booked-ticket")
    public ArrayList<Ticket> getAllBookedTicket() {
    	ArrayList<Ticket> result = new TicketDAO().getAllBookedTicket();
    	return result;
    }

    @PostMapping("/get-booked-tickets-by-movie")
    public ArrayList<Ticket> getBookedTicketListByMovie(@RequestBody Movie movie) {
    	ArrayList<Ticket> result = new TicketDAO().getBookedTicketListByMovie(movie);
    	return result;
    }
    
    @PostMapping("/get-booked-tickets-by-movie-show-schedule")
    public ArrayList<Ticket> getBookedTicketListByMovieShowSchedule(@RequestBody MovieShowSchedule movieShowSchedule) {
    	ArrayList<Ticket> result = new TicketDAO().getBookedTicketListByMovieShowSchedule(movieShowSchedule);
    	return result;
    }
    
    @PostMapping("/get-movie-stat")
    public ArrayList<MovieStat> getMovieStat(@RequestBody List<Bill> billList) {
    	ArrayList<MovieStat> result = new MovieDAO().getMovieStat(billList);
    	return result;
    }
    
    @PostMapping("/get-movie-show-schedule-stat")
    public ArrayList<MovieShowScheduleStat> getMovieStat(@RequestBody Movie movie, @RequestParam int[] billIds) {
    	ArrayList<MovieShowScheduleStat> result = new MovieShowScheduleDAO().getMovieShowScheduleStat(movie, billIds);
    	return result;
    }

    
}