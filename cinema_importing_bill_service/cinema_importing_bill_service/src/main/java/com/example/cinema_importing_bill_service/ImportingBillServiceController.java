package com.example.cinema_importing_bill_service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_importing_bill_service.dao.ImportingBillDAO;

import model.*;



@RestController
@RequestMapping("/api")
public class ImportingBillServiceController {
    @PostMapping("/create-importing-bill")
    public ImportingBill createImportingBill(@RequestBody ImportingBill bill) {
    	System.out.println("BILL RECEIVED " + bill.getManager().getId() + " " + bill.getSupplier().getId());
    	bill = new ImportingBillDAO().createImportingBill(bill);
    	return bill;
    }


    
}