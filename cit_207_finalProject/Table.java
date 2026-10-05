package cit_207_finalProject;

public class Table {
	int number;
    int capacity;
    String location;
    String customer;
    String date;
    String time;
    boolean reserved;

    public Table(int number, int capacity, String location) {
        this.number = number;
        this.capacity = capacity;
        this.location = location;
        this.reserved = false;
    }
}
