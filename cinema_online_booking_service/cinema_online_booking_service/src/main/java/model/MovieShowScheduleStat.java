package model;

public class MovieShowScheduleStat extends MovieShowScheduleDecorator{
	private float revenue;
	private int totalTickets;
	
	public MovieShowScheduleStat() {
		
	}
	// Constructor
    public MovieShowScheduleStat(float revenue, int totalTickets) {
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
