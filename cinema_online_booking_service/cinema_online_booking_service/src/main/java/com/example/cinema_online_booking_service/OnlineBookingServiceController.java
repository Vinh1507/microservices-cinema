package com.example.cinema_online_booking_service;

import java.util.ArrayList;
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
public class OnlineBookingServiceController {

    @PostMapping("/book-tickets")
    public Boolean bookTickets(@RequestBody Bill bill) {
    	if(bill.getCustomer() == null || bill.getTickets() == null || bill.getTickets().size() == 0 || bill.getPayment() == null) {
    		return null;
    	}
    	RestTemplate restTemplate = new RestTemplate();
    	
        User user = bill.getCustomer();
    	
    	
    	String billServiceUrl = "http://192.168.67.2:30083/api/create-bill-and-payment";
    	HttpHeaders headersCreateBill = new HttpHeaders();
    	headersCreateBill.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Bill> request = new HttpEntity<>(bill, headersCreateBill);
    	ResponseEntity<Bill> responseCreateBill = restTemplate.exchange(
			billServiceUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<Bill>() {}
        );
    	
    	bill = responseCreateBill.getBody();
    	
    	System.out.println("BILL: " + bill.getId());
    	
    	String updateTicketsWereBought = "http://192.168.67.2:30082/api/update-booked-ticket-by-bill";
    	HttpHeaders headersUpdateTickets = new HttpHeaders();
    	headersCreateBill.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Bill> request2 = new HttpEntity<>(bill, headersUpdateTickets);
    	ResponseEntity<Boolean> responseUpdateTickets = restTemplate.exchange(
			updateTicketsWereBought,
            HttpMethod.POST,
            request2,
            new ParameterizedTypeReference<Boolean>() {}
        );
    	
    	Boolean updateTicketSuccess = responseUpdateTickets.getBody();
    	return updateTicketSuccess;
    }
    
    
}