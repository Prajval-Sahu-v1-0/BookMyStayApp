import java.util.HashMap;

abstract class Room {

    protected String type;
    protected int beds;
    protected double size;
    protected double price;

    public Room(String type, int beds, double size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price: " + price);
    }
}

/**
 * Single room implementation.
 * @author Student
 * @version 2.0
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200, 1500);
    }
}

/**
 * Double room implementation.
 * @author Student
 * @version 2.0
 */
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350, 2500);
    }
}

/**
 * Suite room implementation.
 * @author Student
 * @version 2.0
 */
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 600, 5000);
    }
}

/**
 * Centralized inventory management using HashMap.
 * @author Student
 * @version 3.0
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

/**
 * Provides read-only search functionality for guests.
 * @author Student
 * @version 4.0
 */
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms(Room[] rooms) {
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getType());
            if (available > 0) {
                room.displayDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println();
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        RoomSearchService searchService = new RoomSearchService(inventory);

        System.out.println("Book My Stay App");
        System.out.println("Version: 4.1");
        System.out.println();
        System.out.println("Available Rooms:");
        System.out.println();

        searchService.searchAvailableRooms(rooms);
    }
}