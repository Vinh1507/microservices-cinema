package com.example.cinema_ticket_bill_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_ticket_bill_service.dao.BillDAO;
import com.example.cinema_ticket_bill_service.dao.PaymentDAO;

import model.Bill;
import model.*;



@RestController
@RequestMapping("/api")
public class TicketBillServiceController {    
    @PostMapping("/create-bill-and-payment")
    public Bill createBillAndPayment(@RequestBody Bill bill) {
    	Payment payment = new PaymentDAO().createPayment(bill.getPayment());
    	bill.setPayment(payment);
    	new BillDAO().createBill(bill);
    	return bill;
    }
    @PostMapping("/get-all-bill")
    public ArrayList<Bill> getAllBill(@RequestParam("start") String start, @RequestParam("end") String end) {
    	return new BillDAO().getAllBill(start, end);
    }
    
    @PostMapping("/get-bill-list-by-ids")
    public ArrayList<Bill> getBillListByIds(@RequestBody ArrayList<Integer> billIds, @RequestParam("start") String start, @RequestParam("end") String end) {
    	return new BillDAO().getBillListByIds(billIds, start, end);
    }
    
}