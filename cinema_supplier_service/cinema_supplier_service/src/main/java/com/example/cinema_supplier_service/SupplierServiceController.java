package com.example.cinema_supplier_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_supplier_service.dao.SupplierDAO;

import model.Bill;
import model.*;



@RestController
@RequestMapping("/api")
public class SupplierServiceController {    
    @GetMapping("/get-supplier-list")
    public ArrayList<Supplier> getSupllierList() {
    	return new SupplierDAO().getSupplierList();
    }
}