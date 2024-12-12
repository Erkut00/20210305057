import java.util.*;

interface Room {
    void bookRoom(String customerName);
    void checkOut();
}

abstract class HotelRoom implements Room {
    protected String roomNumber;
    protected boolean isBooked;

    public HotelRoom(String roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }

    @Override
    public void bookRoom(String customerName) {
        if (!isBooked) {
            isBooked = true;
            System.out.println("Room " + roomNumber + " booked for " + customerName);
        } else {
            System.out.println("Room " + roomNumber + " is already booked.");
        }
    }

    @Override
    public void checkOut() {
        if (isBooked) {
            isBooked = false;
            System.out.println("Checked out from room " + roomNumber);
        } else {
            System.out.println("Room " + roomNumber + " is not booked.");
        }
    }
}

class SingleRoom extends HotelRoom {
    public SingleRoom(String roomNumber) {
        super(roomNumber);
    }
}

class DoubleRoom extends HotelRoom {
    public DoubleRoom(String roomNumber) {
        super(roomNumber);
    }
}

class Hotel {
    private List<HotelRoom> rooms;
    private Set<String> customers;
    private Map<String, HotelRoom> roomMap;

    public Hotel() {
        rooms = new ArrayList<>();
        customers = new HashSet<>();
        roomMap = new HashMap<>();
    }

    public void addRoom(HotelRoom room) {
        rooms.add(room);
        roomMap.put(room.roomNumber, room);
    }

    public void bookRoom(String roomNumber, String customerName) {
        if (customers.add(customerName)) {
            roomMap.get(roomNumber).bookRoom(customerName);
        } else {
            System.out.println("Customer " + customerName + " has already booked a room.");
        }
    }

    public void checkOutRoom(String roomNumber) {
        roomMap.get(roomNumber).checkOut();
    }

    public void displayAvailableRooms() {
        rooms.stream()
                .filter(room -> !room.isBooked)
                .forEach(room -> System.out.println("Available Room: " + room.roomNumber));
    }
}

public class HotelManagementSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        hotel.addRoom(new SingleRoom("101"));
        hotel.addRoom(new DoubleRoom("102"));
        hotel.addRoom(new DoubleRoom("103"));
        hotel.displayAvailableRooms();
        hotel.bookRoom("101", "Erkut");
        hotel.bookRoom("102", "Cankut");
        hotel.checkOutRoom("101");
        hotel.displayAvailableRooms();
    }
}