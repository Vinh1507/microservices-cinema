package model;

public class MovieStat extends MovieDecorator{
	private float revenue;
	private int totalTickets;
	
	public MovieStat() {
		
	}
	// Constructor
    public MovieStat(float revenue, int totalTickets) {
        this.revenue = revenue;
        this.totalTickets = totalTickets;
    }
    
	// Getter method for revenue
    public float getRevenue() {
        return revenue;
    }

    // Setter method for revenue
    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }

    // Getter method for totalTickets
    public int getTotalTickets() {
        return totalTickets;
    }

    // Setter method for totalTickets
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }
}
