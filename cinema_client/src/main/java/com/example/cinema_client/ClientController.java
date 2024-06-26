package com.example.cinema_client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

import model.Bill;
import model.Customer;
import model.ImportingBill;
import model.Movie;
import model.MovieDetail;
import model.MovieShowSchedule;
import model.MovieShowScheduleStat;
import model.MovieStat;
import model.Payment;
import model.Supplier;
import model.Ticket;
import model.User;
import org.springframework.http.*;

@Controller
public class ClientController {
	@Autowired
	private HttpSession session;
	
	@GetMapping("/login")
    public String getLogin(Model model) {
		User userInfo = (User) session.getAttribute("userInfo");
        if(userInfo != null) {
        	return "redirect:/movies";
        }
        return "login";
    }
	
	@PostMapping("/login")
    public String postLogin(Model model, @RequestParam("username") String username,  @RequestParam("password") String password) {
		RestTemplate restTemplate = new RestTemplate();
        String externalServiceUrl = "http://192.168.67.2:30081/api/login";
        String url = "http://192.168.67.2:30082/api/get-movie-show-schedule-by-movie";
        HttpHeaders headers = new HttpHeaders();
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(
            externalServiceUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<User>() {}
        );
        user = responseEntity.getBody();
        if(user != null) {
        	session.setAttribute("userInfo", user);
        	if(user.getRole().equals("manager")) return "redirect:manager-home";
        	return "redirect:home";
        } else {
        	model.addAttribute("error", "Login failed!");
        	return "login";
        }
    }
	
	@GetMapping("/home")
    public String getCustomerHome() {
        return "home";
    }
	    
	@GetMapping("/manager-home")
    public String getManagerHome() {
        return "managerHome";
    }
	
	@GetMapping("/search-movie")
    public String getSearchMovieForm() {
        return "searchMovie";
    }
    
    @GetMapping("/movies")
    public String getMovieList(Model model, @RequestParam(required = false) String key) {
    	if(key == null) {
    		key = "";
    	}
    	RestTemplate restTemplate = new RestTemplate();
        String externalServiceUrl = "http://192.168.67.2:30082/api/get-movie-list?key="+key;
        ResponseEntity<List<Movie>> responseEntity = restTemplate.exchange(
            externalServiceUrl,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Movie>>() {}
        );

        
        List<Movie> movies = responseEntity.getBody();
        session.setAttribute("movies", movies);
        model.addAttribute("movies", movies);
        return "movieResult";
    }
    
    @GetMapping("/movie-detail")
    public String getMovieShowScheduleByMovie(Model model, @RequestParam int id) {
    	System.out.println(id);
    	Movie movie = null;
    	List<Movie> movieList = (List<Movie>) session.getAttribute("movies");
    	for(Movie x : movieList) {
    		if(x.getId() == id) {
    			movie = x;
    			break;
    		}
    	}
    	
    	User userInfo = (User) session.getAttribute("userInfo");
        
    	RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.67.2:30082/api/get-movie-show-schedule-by-movie";
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Movie> request = new HttpEntity<>(movie, headers);
        ResponseEntity<ArrayList<MovieShowSchedule>> responseEntity = restTemplate.exchange(
        		url, 
                HttpMethod.POST, 
                request, 
                new ParameterizedTypeReference<ArrayList<MovieShowSchedule>>() {}
        );
        ArrayList<MovieShowSchedule> movieShowSchedules = responseEntity.getBody();
        session.setAttribute("movieShowScheduleList", movieShowSchedules);
        model.addAttribute("movieDetail", movie);
        model.addAttribute("movieShowSchedules", movieShowSchedules);
        return "movieDetail";
    }
    
    @GetMapping("/movie-show-schedule-detail/{movieShowScheduleId}")
    public String getMovieShowScheduleTickets(Model model, @PathVariable int movieShowScheduleId) {
    	MovieShowSchedule movieShowSchedule = null;
    	List<MovieShowSchedule> mssList = (List<MovieShowSchedule>) session.getAttribute("movieShowScheduleList");
    	for(MovieShowSchedule x : mssList) {
    		if(x.getId() == movieShowScheduleId) {
    			movieShowSchedule = x;
    			break;
    		}
    	}
        RestTemplate restTemplate = new RestTemplate();
        String externalServiceUrl = "http://192.168.67.2:30082/api/get-ticket-list-by-movie-show-schedule";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MovieShowSchedule> request = new HttpEntity<>(movieShowSchedule, headers);
        ResponseEntity<ArrayList<Ticket>> responseEntity = restTemplate.exchange(
    		externalServiceUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<ArrayList<Ticket>>() {}
        );
        
        ArrayList<Ticket> ticketList = responseEntity.getBody();
        session.setAttribute("ticketList", ticketList);
        session.setAttribute("mss", ticketList.get(0).getMovieShowSchedule());
        session.setAttribute("movie", ticketList.get(0).getMovieShowSchedule().getMovie());
        model.addAttribute("ticketList", ticketList);
        model.addAttribute("totalRows", ticketList.get(0).getSeat().getRoom().getTotalRows());
        model.addAttribute("totalColumns", ticketList.get(0).getSeat().getRoom().getTotalColumns());
        model.addAttribute("mss", ticketList.get(0).getMovieShowSchedule());
        model.addAttribute("movie", ticketList.get(0).getMovieShowSchedule().getMovie());
        
        System.out.println(ticketList.get(0).getMovieShowSchedule().getMovie().getTitle());
        return "movieShowScheduleDetail"; 
    }
    
    
    @PostMapping("/payment-booking")
    public String getPaymentBooking(Model model, @RequestParam("book-ticket") int[] ids) {
        ArrayList<Ticket> ticketList = (ArrayList<Ticket>) session.getAttribute("ticketList");
        if(ticketList == null) {
        	return "redirect:/movies";
        }
        ArrayList<Ticket> bookedTicketList = new ArrayList<Ticket>();
        for(Ticket ticket : ticketList) {
        	for(int selectedId : ids) {
        		if(ticket.getId() == selectedId) {
        			bookedTicketList.add(ticket);
        		}
        	}
        }
        Bill bill = new Bill();
        bill.setTickets(bookedTicketList);
        User userInfo = (User) session.getAttribute("userInfo");
        if(userInfo == null) {
        	return "redirect:/login";
        }
        bill.setCustomer(new Customer(userInfo.getId(), userInfo.getAddress(), userInfo.getFullname(), null, 0));
        RestTemplate restTemplate = new RestTemplate();
        String ticketAmountServiceUrl = "http://192.168.67.2:30082/api/calculate-total-amount";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ArrayList<Ticket>> request = new HttpEntity<>(bill.getTickets(), headers);
        ResponseEntity<Float> responseTotalAmount = restTemplate.exchange(
    		ticketAmountServiceUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<Float>() {}
        );
        Float totalAmount = responseTotalAmount.getBody();
        bill.setTotalAmount(totalAmount);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("bookedTicketList", bookedTicketList);
        session.setAttribute("bookedTicketList", bookedTicketList);
        session.setAttribute("bill", bill);
        model.addAttribute("movie", (Movie)session.getAttribute("movie"));
        model.addAttribute("mss", (MovieShowSchedule)session.getAttribute("mss"));
        return "paymentBooking"; 
    }
    
    @PostMapping("/book-tickets")
    public String handleBookTickets(Model model, @RequestParam("paymentMethod") String paymentMethod) {
    	Payment payment = new Payment();
    	payment.setPaymentMethod(paymentMethod);
    	
    	Bill bill = (Bill)session.getAttribute("bill");
    	bill.setPayment(payment);
        RestTemplate restTemplate = new RestTemplate();

        User userInfo = (User) session.getAttribute("userInfo");
        if(userInfo == null) {
        	return "redirect:/login";
        }
        if(bill.getTickets() == null) {
        	return "redirect:/movies";
        }
        String externalServiceUrl = String.format("http://192.168.67.2:30080/api/book-tickets");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Bill> request = new HttpEntity<>(bill, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
    		externalServiceUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<String>() {}
        );
        return "bookingSuccess"; 
    }
    
    
    
    @GetMapping("/import-movie")
    public String getImportingMovieForm(Model model) {
    	RestTemplate restTemplate = new RestTemplate();
        String urlMovies = "http://192.168.67.2:30082/api/get-importing-movie-list";
        ResponseEntity<List<Movie>> responseEntity = restTemplate.exchange(
    		urlMovies,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Movie>>() {}
        );

        List<Movie> movies = responseEntity.getBody();
        session.setAttribute("importingMovieList", movies);
        model.addAttribute("importingMovieList", movies);
        
        String urlSuppliers = "http://192.168.67.2:30086/api/get-supplier-list";
        ResponseEntity<List<Supplier>> responseSuppliers = restTemplate.exchange(
    		urlSuppliers,
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<Supplier>>() {}
        );

        List<Supplier> supplierList = responseSuppliers.getBody();
        session.setAttribute("supplierList", supplierList);
        model.addAttribute("supplierList", supplierList);
        
        return "importingMovie";
    }
    
    @PostMapping("/handle-import-bill")
    public String handleImportBill(Model model, @RequestParam("movieId") int[] movieIds, @RequestParam("supplierId") int supplierId) {
    	User userInfo = (User) session.getAttribute("userInfo");
        if(userInfo == null) {
        	return "redirect:/login";
        }
    	ArrayList<Movie> movieList = (ArrayList<Movie>) session.getAttribute("importingMovieList");
    	ArrayList<MovieDetail> movieDetails = new ArrayList<MovieDetail>();
    	float totalAmount = 0;
    	for(Movie movie : movieList) {
    		for(int id : movieIds) {
    			if(movie.getId() == id) {
    				MovieDetail movieDetail = new MovieDetail();
    				movieDetail.setMovie(movie);
    				movieDetail.setImportingPrice(movie.getPrice());
    				movieDetails.add(movieDetail);
    				totalAmount += movie.getPrice();
    			}
    		}
    	}
    	
    	ArrayList<Supplier> supplierList = (ArrayList<Supplier>) session.getAttribute("supplierList");
    	Supplier supplier = null;
    	for(Supplier x : supplierList) {
    		if(x.getId() == supplierId) {
    			supplier = x;
    		}
    	}
    	
    	ImportingBill bill = new ImportingBill();
    	bill.setMovieDetails(movieDetails);
    	bill.setSupplier(supplier);
    	bill.setManager(userInfo);
    	bill.setTotalAmount(totalAmount);
    	
    	RestTemplate restTemplate = new RestTemplate();
    	String externalServiceUrl = String.format("http://192.168.67.2:30087/api/handle-import-bill"); // HandleImportMovieService
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ImportingBill> request = new HttpEntity<>(bill, headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
    		externalServiceUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<Boolean>() {}
        );
        return "importingSuccess";
    }
    
    @GetMapping("/movie-stat-form")
    public String getMovieStatForm() {
        return "movieStatForm";
    }
    
    @PostMapping("/get-movie-stat")
    public String getMovieStat(Model model, @RequestParam("start") String start, @RequestParam("end") String end) {
    	RestTemplate restTemplate = new RestTemplate();
        String movieStatUrl = "http://192.168.67.2:30089/api/handle-movie-stat?start="+start+"&end="+end;
        ResponseEntity<List<MovieStat>> responseMovieStat = restTemplate.exchange(
    		movieStatUrl,
            HttpMethod.POST,
            null,
            new ParameterizedTypeReference<List<MovieStat>>() {}
        );

        List<MovieStat> movieStatList = responseMovieStat.getBody();
        
        for(MovieStat x : movieStatList) {
        	System.out.println(x.getMovie().getTitle() + " " + x.getRevenue() + " " + x.getTotalTickets());
        }
        
        session.setAttribute("start", start);
        session.setAttribute("end", end);
        session.setAttribute("movieStatList", movieStatList);
        model.addAttribute("movieStatList", movieStatList);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "movieStat";
    }
    
    
    @GetMapping("/movie-show-schedule-stat")
    public String getMovieShowScheduleStat(Model model, @RequestParam("movieId") int movieId) {
    	RestTemplate restTemplate = new RestTemplate();
    	String start = (String) session.getAttribute("start");
    	String end = (String) session.getAttribute("end");
    	List<MovieStat> movieStatList = (List<MovieStat>) session.getAttribute("movieStatList");
        
        Movie movie = null;
        for(MovieStat ms : movieStatList) {
        	if(ms.getMovie().getId() == movieId) {
        		movie = ms.getMovie();
        		break;
        	}
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Movie> request = new HttpEntity<>(movie, headers);
        
        String mssStatUrl = "http://192.168.67.2:30089/api/handle-movie-show-schedule-stat?start="+start+"&end="+end;
        ResponseEntity<List<MovieShowScheduleStat>> responseMSSStat = restTemplate.exchange(
    		mssStatUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<List<MovieShowScheduleStat>>() {}
        );

        List<MovieShowScheduleStat> movieShowScheduleStatList = responseMSSStat.getBody();
        for(MovieShowScheduleStat x : movieShowScheduleStatList) {
        	System.out.println(x.getMovieShowSchedule().getSchedule().getStartTime() + " " + x.getMovieShowSchedule().getId() + " " + x.getRevenue() + " " + x.getTotalTickets());
        }
        session.setAttribute("movieShowScheduleStatList", movieShowScheduleStatList);
        model.addAttribute("movieShowScheduleStatList", movieShowScheduleStatList);
        model.addAttribute("movie", movie);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "movieShowScheduleStat";
    }
    
    @GetMapping("/bill-stat")
    public String getBillListByMovieShowSchedule(Model model, @RequestParam("movieShowScheduleId") int movieShowScheduleId) {
    	RestTemplate restTemplate = new RestTemplate();
    	String start = (String) session.getAttribute("start");
    	String end = (String) session.getAttribute("end");
    	List<MovieShowScheduleStat> mssList = (List<MovieShowScheduleStat>) session.getAttribute("movieShowScheduleStatList");
        
        MovieShowSchedule movieShowSchedule = null;
        float totalRevenue = 0;
        for(MovieShowScheduleStat mss : mssList) {
        	if(mss.getMovieShowSchedule().getId() == movieShowScheduleId) {
        		movieShowSchedule = mss.getMovieShowSchedule();
        		totalRevenue = mss.getRevenue();
        		break;
        	}
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MovieShowSchedule> request = new HttpEntity<>(movieShowSchedule, headers);
        
        String billStatUrl = "http://192.168.67.2:30089/api/get-bill-list-by-movie-show-schedule?start="+start+"&end="+end;
        ResponseEntity<List<Bill>> responseBillStat = restTemplate.exchange(
    		billStatUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<List<Bill>>() {}
        );

        List<Bill> billList = responseBillStat.getBody();
        
        System.out.println("BILL CLIENT: " + billList.size());
        model.addAttribute("movieShowSchedule", movieShowSchedule);
        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("movie", movieShowSchedule.getMovie());
        model.addAttribute("billList", billList);
        model.addAttribute("start", start);
        model.addAttribute("end", end);
        return "billResult";
    }
    
    @GetMapping("/")
    public String Home() {
        return "redirect:/movies";
    }
    
    
    
}