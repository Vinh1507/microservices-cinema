package model;
public class User {
	private int id;
	private Address address;
	private Fullname fullname;
	private String username, password;
	private String role;
	
	public User() {
		
	}
	
	public User(int id, Address address, Fullname fullname, String username, String password) {
        this.id = id;
        this.address = address;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public User(int id, Address address, Fullname fullname) {
        this.id = id;
        this.address = address;
        this.fullname = fullname;
    }
    
    public User(int id, Fullname fullname, String username) {
    	this.id = id;
    	this.fullname = fullname;
    	this.username = username;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public Fullname getFullname() {
        return fullname;
    }
    
    public String getUsername() {
		return username;
	}
    
    public String getPassword() {
		return password;
	}
    
    public String getRole() {
		return role;
	}

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setFullname(Fullname fullname) {
        this.fullname = fullname;
    }
    
    public void setUsername(String username) {
		this.username = username;
	}
    
    public void setPassword(String password) {
		this.password = password;
	}
    
    public void setRole(String role) {
		this.role = role;
	}
}
