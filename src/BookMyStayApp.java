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

    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price: " + price);
    }
}
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 200, 1500);
    }
}

class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 350, 2500);
    }
}

class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 600, 5000);
    }
}


public class BookMyStayApp {

    public static void main(String[] args) {

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("Book My Stay App");
        System.out.println("Version: 2.1");

        System.out.println();
        single.displayDetails();
        System.out.println("Available Rooms: " + singleAvailability);

        System.out.println();
        doubleRoom.displayDetails();
        System.out.println("Available Rooms: " + doubleAvailability);

        System.out.println();
        suite.displayDetails();
        System.out.println("Available Rooms: " + suiteAvailability);
    }
}