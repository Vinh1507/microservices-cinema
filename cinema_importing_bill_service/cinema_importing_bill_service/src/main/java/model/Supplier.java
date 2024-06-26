package model;

public class Supplier {
    private int id;
    private String email, contact;
    
    public Supplier(int id, String email, String contact) {
        this.id = id;
        this.email = email;
        this.contact = contact;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getContact() {
        return contact;
    }
    
    public void setContact(String contact) {
        this.contact = contact;
    }
}
