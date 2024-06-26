package model;
public class Seat {
	private int id;
	private String position;
	private Room room;
	private int seatRow, seatColumn;
	

    // Constructors
    public Seat() {
    }

    public Seat(int id, String position, Room room) {
        this.id = id;
        this.position = position;
        this.room = room;
    }
    
    public Seat(int id, String position, Room room, int row, int col) {
        this.id = id;
        this.position = position;
        this.room = room;
        this.seatRow = row;
        this.seatColumn = col;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public Room getRoom() {
        return room;
    }
    
    public int getSeatRow() {
		return seatRow;
	}
    
    public int getSeatColumn() {
		return seatColumn;
	}

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    
    public void setSeatRow(int seatRow) {
		this.seatRow = seatRow;
	}
    
    public void setSeatColumn(int seatColumn) {
		this.seatColumn = seatColumn;
	}
}
