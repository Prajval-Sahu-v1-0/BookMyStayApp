import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

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

    public void decrement(String roomType) {
        int current = inventory.getOrDefault(roomType, 0);
        if (current > 0) {
            inventory.put(roomType, current - 1);
        }
    }
}

class BookingService {

    private RoomInventory inventory;
    private HashMap<String, Set<String>> allocatedRooms;
    private int idCounter = 1;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    private String generateRoomId(String roomType) {
        String id = roomType.replace(" ", "").substring(0, 2).toUpperCase() + idCounter;
        idCounter++;
        return id;
    }

    public void processReservation(Reservation reservation) {

        String type = reservation.getRoomType();
        int available = inventory.getAvailability(type);

        if (available <= 0) {
            System.out.println("Reservation failed for " + reservation.getGuestName() + " (No rooms available)");
            return;
        }

        String roomId = generateRoomId(type);

        allocatedRooms.putIfAbsent(type, new HashSet<>());
        Set<String> rooms = allocatedRooms.get(type);

        if (!rooms.contains(roomId)) {
            rooms.add(roomId);
            inventory.decrement(type);
            System.out.println("Reservation confirmed for " + reservation.getGuestName() + " | Room ID: " + roomId);
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));
        queue.addRequest(new Reservation("David", "Single Room"));

        BookingService service = new BookingService(inventory);

        System.out.println("Book My Stay App");
        System.out.println("Version: 6.1");
        System.out.println();

        while (queue.hasRequests()) {
            Reservation r = queue.getNextRequest();
            service.processReservation(r);
        }
    }
}