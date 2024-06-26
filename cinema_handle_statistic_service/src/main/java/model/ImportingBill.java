package model;

import java.util.ArrayList;


public class ImportingBill {
    private int id;
    private User manager;
    private String importDate;
    private float totalAmount;
    private String note;
    private ArrayList<MovieDetail> movieDetails;
    private Supplier supplier;

    // Constructor
    public ImportingBill() {
    	
    }
    
    public ImportingBill(int id, User manager, String importDate, float totalAmount, String note, ArrayList<MovieDetail> movieDetails, Supplier supplier) {
        this.id = id;
        this.manager = manager;
        this.importDate = importDate;
        this.totalAmount = totalAmount;
        this.note = note;
        this.movieDetails = movieDetails;
        this.supplier = supplier;
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for manager
    public User getManager() {
        return manager;
    }

    // Setter for manager
    public void setManager(User manager) {
        this.manager = manager;
    }

    // Getter for importDate
    public String getImportDate() {
        return importDate;
    }

    // Setter for importDate
    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    // Getter for totalAmount
    public float getTotalAmount() {
        return totalAmount;
    }

    // Setter for totalAmount
    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Getter for note
    public String getNote() {
        return note;
    }

    // Setter for note
    public void setNote(String note) {
        this.note = note;
    }

    // Getter for movieDetails
    public ArrayList<MovieDetail> getMovieDetails() {
        return movieDetails;
    }

    // Setter for movieDetails
    public void setMovieDetails(ArrayList<MovieDetail> movieDetails) {
        this.movieDetails = movieDetails;
    }

    // Getter for supplier
    public Supplier getSupplier() {
        return supplier;
    }

    // Setter for supplier
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
