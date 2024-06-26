package model;

public class Payment {
	private int id;
	private String paymentMethod;
	private String paymentNote;
	
	// Constructor
	public Payment() {
		
	}
	
    public Payment(int id, String paymentMethod, String paymentNote) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.paymentNote = paymentNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentNote() {
		return paymentNote;
	}

    public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentNote=" + paymentNote +
                '}';
    }
}
