package model;

import java.util.ArrayList;

public class Bill {
	private int id;
	private Customer customer;
	private ArrayList<Ticket> tickets;
	private int quantity;
	private float totalAmount;
	private String bookingTime;
	private Payment payment;
	
	public Bill() {
		
	}
	
    public Bill(int id, Customer customer, ArrayList<Ticket> tickets, int quantity, float totalAmount, String bookingTime, Payment payment) {
        this.id = id;
        this.customer = customer;
        this.tickets = tickets;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.bookingTime = bookingTime;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(String bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customer=" + customer +
                ", tickets=" + tickets +
                ", quantity=" + quantity +
                ", totalAmount=" + totalAmount +
                ", bookingTime='" + bookingTime + '\'' +
                ", payment=" + payment +
                '}';
    }
}
