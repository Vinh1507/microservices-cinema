package model;
public class MovieShowSchedule {
	private int id;
	private Room room;
	private Schedule schedule;
	private Movie movie;
	
	public MovieShowSchedule() {
    }

    public MovieShowSchedule(int id, Room room, Schedule schedule, Movie movie) {
        this.id = id;
        this.room = room;
        this.schedule = schedule;
        this.movie = movie;
    }
    
    public MovieShowSchedule(int id) {
        this.id = id;
    }
    
    public MovieShowSchedule(Movie movie) {
        this.movie = movie;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public Movie getMovie() {
        return movie;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
