package model;

import java.text.NumberFormat;

public class Movie {
	private int id;
	private String title, category, description;
	private float price;
	

    // Constructors
    public Movie() {
    }

    public Movie(int id, String title, String category, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
    }
    
    public Movie(int id, String title, String category, String description, float price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }
    
    public Movie(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    
    public Movie(int id) {
        this.id = id;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    
    public String getCategory() {
		return category;
	}
    
    public float getPrice() {
		return price;
	}
    
    public String getFormatPrice() {
    	NumberFormat formatter = NumberFormat.getInstance();
        String formattedNumber = formatter.format(price);
        return formattedNumber;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setCategory(String category) {
		this.category = category;
	}
    
    public void setPrice(float price) {
		this.price = price;
	}
}
