package model;
public class Ticket {
	private int id;
	private Seat seat;
	private MovieShowSchedule movieShowSchedule;
	private double price;
	private boolean isAvailable;
	private Bill bill;

    // Constructors
    public Ticket() {
    }

    public Ticket(int id, Seat seat, MovieShowSchedule movieShowSchedule, double price) {
        this.id = id;
        this.seat = seat;
        this.movieShowSchedule = movieShowSchedule;
        this.price = price;
    }
    
    public Ticket(int id, Seat seat, MovieShowSchedule movieShowSchedule, double price, boolean isAvailable) {
        this.id = id;
        this.seat = seat;
        this.movieShowSchedule = movieShowSchedule;
        this.price = price;
        this.isAvailable = isAvailable;
    }
    
    public Ticket(int id, MovieShowSchedule movieShowSchedule, double price) {
        this.id = id;
        this.movieShowSchedule = movieShowSchedule;
        this.price = price;
    }
    

    // Getter methods
    public int getId() {
        return id;
    }

    public Seat getSeat() {
        return seat;
    }

    public MovieShowSchedule getMovieShowSchedule() {
        return movieShowSchedule;
    }

    public double getPrice() {
        return price;
    }
    
    public boolean getIsAvailable() {
    	return isAvailable;
    }
    
    public Bill getBill() {
		return bill;
	}

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setMovieShowSchedule(MovieShowSchedule movieShowSchedule) {
        this.movieShowSchedule = movieShowSchedule;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public void setIsAvailable(boolean isAvailable) {
    	this.isAvailable = isAvailable;
    }
    
    public void setBill(Bill bill) {
		this.bill = bill;
	} 
}
