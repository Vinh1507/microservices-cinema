package model;

public class MovieDecorator {
	private Movie movie;
	
	public MovieDecorator() {
		
	}
	
	public MovieDecorator(Movie movie) {
		this.movie = movie;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}
