package com.example.cinema_handle_statistic_service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import model.*;

@RestController
@RequestMapping("/api")
public class HandleStatisticServiceController {
	

	@PostMapping("/handle-movie-stat")
	public ArrayList<MovieStat> handleMovieStat(@RequestParam("start") String start, @RequestParam("end") String end) {
		System.out.println("handleMovieStat" + start + " " + end);
		RestTemplate restTemplate = new RestTemplate();

		String allBillUrl = "http://192.168.67.2:30083/api/get-all-bill?start=" + start + "&end=" + end;
		ResponseEntity<List<Bill>> responseBill = restTemplate.exchange(allBillUrl, HttpMethod.POST, null,
			new ParameterizedTypeReference<List<Bill>>() {
		});
		List<Bill> billList = responseBill.getBody();
		
        String url = "http://192.168.67.2:30082/api/get-movie-stat";
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<List<Bill>> request = new HttpEntity<>(billList, headers);
        ResponseEntity<ArrayList<MovieStat>> responseEntity = restTemplate.exchange(
        		url, 
                HttpMethod.POST, 
                request, 
                new ParameterizedTypeReference<ArrayList<MovieStat>>() {}
        );
        ArrayList<MovieStat> movieStatResult = responseEntity.getBody();
		return movieStatResult;
	}

	@PostMapping("/handle-movie-show-schedule-stat")
	public ArrayList<MovieShowScheduleStat> handleMovieShowScheduleStat(@RequestBody Movie movie,
			@RequestParam("start") String start, @RequestParam("end") String end) {
		RestTemplate restTemplate = new RestTemplate();

		String allBillUrl = "http://192.168.67.2:30083/api/get-all-bill?start=" + start + "&end=" + end;
		ResponseEntity<List<Bill>> responseBill = restTemplate.exchange(allBillUrl, HttpMethod.POST, null,
			new ParameterizedTypeReference<List<Bill>>() {
		});
		List<Bill> billList = responseBill.getBody();
		
		String billIds = "";
    	for(int i = 0; i < billList.size(); i++) {
    		billIds += "billIds=" + billList.get(i).getId();
    		if(i < billList.size()-1) {
    			billIds += "&";
    		}
    	}
		System.out.println("BILL IDS: " + billIds);
        String url = "http://192.168.67.2:30082/api/get-movie-show-schedule-stat?" + billIds;
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<Movie> request = new HttpEntity<>(movie, headers);
        ResponseEntity<ArrayList<MovieShowScheduleStat>> responseEntity = restTemplate.exchange(
        		url, 
                HttpMethod.POST, 
                request, 
                new ParameterizedTypeReference<ArrayList<MovieShowScheduleStat>>() {}
        );
        ArrayList<MovieShowScheduleStat> movieShowScheduleStatList = responseEntity.getBody();
		return movieShowScheduleStatList;
	}

	@PostMapping("/get-bill-list-by-movie-show-schedule")
	public List<Bill> getBillListByMovieShowSchedule(@RequestBody MovieShowSchedule movieShowSchedule,
			@RequestParam("start") String start, @RequestParam("end") String end) {
		System.out.println("getBillListByMovieShowSchedule" + movieShowSchedule.getId() + start + " " + end);
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<MovieShowSchedule> request = new HttpEntity<>(movieShowSchedule, headers);
		String ticketsUrl = "http://192.168.67.2:30082/api/get-booked-tickets-by-movie-show-schedule";
		ResponseEntity<List<Ticket>> responseTicket = restTemplate.exchange(ticketsUrl, HttpMethod.POST, request,
			new ParameterizedTypeReference<List<Ticket>>() {
		});

		List<Ticket> ticketList = responseTicket.getBody();

		System.out.println("BILL TICKET: " + ticketList.size());

		HashSet<Integer> billIdSet = new HashSet<Integer>();
		
		for (Ticket ticket : ticketList) {
			billIdSet.add(ticket.getBill().getId());
		}
		ArrayList<Integer> billIds = new ArrayList<Integer>();
		for (int x : billIdSet) {
			billIds.add(x);
		}

		String billUrl = "http://192.168.67.2:30083/api/get-bill-list-by-ids?start=" + start + "&end=" + end;
		HttpHeaders headersBill = new HttpHeaders();
		HttpEntity<ArrayList<Integer>> requestBill = new HttpEntity<>(billIds, headersBill);
		ResponseEntity<List<Bill>> responseBill = restTemplate.exchange(billUrl, HttpMethod.POST, requestBill,
			new ParameterizedTypeReference<List<Bill>>() {
		});
		List<Bill> billList = responseBill.getBody();
		
		for (Bill bill : billList) {
			int billId = bill.getId();
			float totalAmount = 0;
			int totalQuantity = 0;
			for (Ticket ticket : ticketList) {
				if (ticket.getBill().getId() == billId) {
					totalAmount += ticket.getPrice();
					totalQuantity++;
					System.out.println(billId + " PRICE " + ticket.getPrice());
				}
			}
			System.out.println(billId + " " + totalQuantity + " " + totalAmount);
			bill.setQuantity(totalQuantity);
			bill.setTotalAmount(totalAmount);
		}
		
		System.out.println("BILL TICKET billList: " + billList.size());

		HashSet<Integer> customerIdSet = new HashSet<Integer>();
		for (Bill bill : billList) {
			customerIdSet.add(bill.getCustomer().getId());
		}
		ArrayList<Integer> userIds = new ArrayList<Integer>();
		for (int x : customerIdSet) {
			System.out.println("ID CUST" + x);
			userIds.add(x);
		}

		String customerUrl = "http://192.168.67.2:30081/api/get-user-by-ids";
		HttpHeaders headersCustomer = new HttpHeaders();
		headersCustomer.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<ArrayList<Integer>> requestCustomer = new HttpEntity<>(userIds, headersCustomer);
		ResponseEntity<List<User>> responseCustomer = restTemplate.exchange(customerUrl, HttpMethod.POST,
			requestCustomer, new ParameterizedTypeReference<List<User>>() {
		});
		List<User> customerList = responseCustomer.getBody();
		System.out.println("BILL CUSTOMER: " + customerList.size());
		for (Bill bill : billList) {
			for (User customer : customerList) {
				if (bill.getCustomer() != null && bill.getCustomer().getId() == customer.getId()) {
					bill.setCustomer(new Customer(0, null, customer.getFullname(), null, 0));
//        			System.out.println(bill.getCustomer().getFullname().getFirstName());
				}
			}
		}
		return billList;
	}
}