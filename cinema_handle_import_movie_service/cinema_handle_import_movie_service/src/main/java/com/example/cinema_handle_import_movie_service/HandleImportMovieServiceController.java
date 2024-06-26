package com.example.cinema_handle_import_movie_service;

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
public class HandleImportMovieServiceController {

    @PostMapping("/handle-import-bill")
    public Boolean handleImportBill(@RequestBody ImportingBill bill) {
    	System.out.println("HandleImportMovieServiceController" + bill.getTotalAmount());
    	if(bill.getManager() == null || bill.getMovieDetails() == null || bill.getSupplier() == null) {
    		return null;
    	}
    	RestTemplate restTemplate = new RestTemplate();
    	
    	String createImportBillUrl = "http://192.168.67.2:30088/api/create-importing-bill";
    	HttpHeaders headersCreateBill = new HttpHeaders();
    	headersCreateBill.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ImportingBill> request = new HttpEntity<>(bill, headersCreateBill);
    	ResponseEntity<ImportingBill> responseCreateBill = restTemplate.exchange(
			createImportBillUrl,
            HttpMethod.POST,
            request,
            new ParameterizedTypeReference<ImportingBill>() {}
        );

    	bill = responseCreateBill.getBody();    	
    	System.out.println("IMPORTING BILL: " + bill.getId());
   	
    	String createMovieDetailUrl = "http://192.168.67.2:30082/api/create-import-movie-detail";
    	HttpHeaders headersMovieDetails = new HttpHeaders();
    	headersMovieDetails.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ImportingBill> request2 = new HttpEntity<>(bill, headersMovieDetails);
    	ResponseEntity<Boolean> responseCreateMovieDetai = restTemplate.exchange(
			createMovieDetailUrl,
            HttpMethod.POST,
            request2,
            new ParameterizedTypeReference<Boolean>() {}
        );

    	Boolean createMovieDetaiSuccess = responseCreateMovieDetai.getBody();
    	return createMovieDetaiSuccess;
    }
    
    
}