import java.util.HashMap;
import java.util.Map;

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

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}

/**
 * Entry point demonstrating centralized room inventory management.
 * @author Student
 * @version 3.1
 */
public class UseCase3RoomInventory {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        System.out.println("Book My Stay App");
        System.out.println("Version: 3.1");

        System.out.println("\nCurrent Room Inventory:");
        inventory.displayInventory();

        System.out.println("\nUpdating availability...");

        inventory.updateAvailability("Single Room", 4);
        inventory.updateAvailability("Double Room", 2);

        System.out.println("\nUpdated Room Inventory:");
        inventory.displayInventory();
    }
}