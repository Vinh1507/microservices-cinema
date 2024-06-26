package model;

public class MovieShowScheduleDecorator {
	private MovieShowSchedule movieShowSchedule;
	
	public MovieShowScheduleDecorator() {
		// TODO Auto-generated constructor stub
	}
	
	public MovieShowScheduleDecorator(MovieShowSchedule movieShowSchedule) {
		this.movieShowSchedule = movieShowSchedule;
	}
	
	public MovieShowSchedule getMovieShowSchedule() {
		return movieShowSchedule;
	}
	
	public void setMovieShowSchedule(MovieShowSchedule movieShowSchedule) {
		this.movieShowSchedule = movieShowSchedule;
	}
}
