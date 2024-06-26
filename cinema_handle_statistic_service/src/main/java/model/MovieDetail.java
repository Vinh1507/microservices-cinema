package model;

public class MovieDetail {
    private int id;
    private Movie movie;
    private float importingPrice;
    private String note;

    public MovieDetail() {
    	
    }
    
    public MovieDetail(int id, Movie movie, float importingPrice, String note) {
        this.id = id;
        this.movie = movie;
        this.importingPrice = importingPrice;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public float getImportingPrice() {
        return importingPrice;
    }

    public void setImportingPrice(float importingPrice) {
        this.importingPrice = importingPrice;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

