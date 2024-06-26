package model;
public class Room {
	private int id;
	private String type, position;
	private int totalRows, totalColumns;
	

    // Constructors
    public Room() {
    }

    public Room(int id, String type, String position) {
        this.id = id;
        this.type = type;
        this.position = position;
    }
    
    public Room(int id, String type, String position, int rows, int cols) {
        this.id = id;
        this.type = type;
        this.position = position;
        this.totalRows = rows;
        this.totalColumns = cols;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPosition() {
        return position;
    }
    
    public int getTotalRows() {
		return totalRows;
	}
    
    public int getTotalColumns() {
		return totalColumns;
	}

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
    
    public void setTotalColumns(int totalColumns) {
		this.totalColumns = totalColumns;
	}
}
