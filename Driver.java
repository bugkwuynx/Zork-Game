import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    static Location currLocation;
    static ContainerItem myInventory;

    static void createWorld() {
        // Initialize inventory
        myInventory = new ContainerItem("Inventory", "container", "A container for items");

        // Create locations
        Location kitchen = new Location("Kitchen", "You are in the kitchen");
        Location hallway = new Location("Hallway", "You are in the hallway");
        Location diningRoom = new Location("Dining Room", "You are in the dining room");
        Location bedRoom = new Location("Bedroom", "You are in the bedroom");

        /*
         * |kitchen| ---- |hallway|
         * | |
         * | |
         * |bedroom| ---- |dining room|
         */

        // Connect locations
        kitchen.connect("west", hallway);
        kitchen.connect("south", bedRoom);
        hallway.connect("east", kitchen);
        hallway.connect("south", diningRoom);
        diningRoom.connect("north", hallway);
        diningRoom.connect("west", bedRoom);
        bedRoom.connect("east", diningRoom);
        bedRoom.connect("north", kitchen);

        // Add items to locations
        kitchen.addItem(new Item("Knife", "A sharp blade for cutting food", "This is a test object"));
        kitchen.addItem(new Item("Turkey", "A large bird for Thanksgiving", "This is another test object"));
        hallway.addItem(new Item("Key", "A key for the door", "This is another test object"));
        diningRoom.addItem(new Item("Wine", "A bottle of wine", "This is another test object"));
        bedRoom.addItem(new Item("Lamp", "A lamp for lighting the room", "This is another test object"));

        // Set initial location
        currLocation = kitchen;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        createWorld();

        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            String[] commandParts = command.split(" ");

            switch (commandParts[0]) {
                case "inventory": {
                    if (commandParts.length != 1) {
                        System.out.println("I don't know how to do that");
                        break;
                    }
                    ArrayList<Item> items = myInventory.getItems();
                    for (Item item : items) {
                        System.out.println(item.getName());
                    }
                    break;
                }
                case "take": {
                    if (commandParts.length != 2) {
                        System.out.println("Please enter the item you want to take!");
                        break;
                    }
                    if (currLocation.hasItem(commandParts[1])) {
                        myInventory.addItem(currLocation.getItem(commandParts[1]));
                        currLocation.removeItem(commandParts[1]);
                    } else {
                        System.out.println("Cannot find that item here");
                    }
                    break;
                }
                case "drop": {
                    if (commandParts.length != 2) {
                        System.out.println("Please enter the item you want to drop!");
                        break;
                    }
                    if (myInventory.hasItem(commandParts[1])) {
                        currLocation.addItem(myInventory.removeItem(commandParts[1]));
                    } else {
                        System.out.println("Cannot find that item in your inventory");
                    }
                    break;
                }
                case "go": {
                    if (commandParts.length != 2) {
                        System.out.println("Please enter the direction!");
                    } else if (!commandParts[1].equals("north") && !commandParts[1].equals("south")
                            && !commandParts[1].equals("east") && !commandParts[1].equals("west")) {
                        System.out.println("Please enter a valid direction!");
                    } else {
                        Location tempLocation = currLocation.getLocation(commandParts[1]);
                        if (tempLocation == null) {
                            System.out.println("There is no path in that direction");
                        } else {
                            currLocation = tempLocation;
                        }
                    }
                    break;
                }
                case "quit": {
                    scanner.close();
                    return;
                }
                case "look": {
                    if (commandParts.length != 1) {
                        System.out.println("I don't know how to do that");
                        break;
                    }
                    System.out.println(String.format("%s - %s has the following items:", currLocation.getName(),
                            currLocation.getDescription()));
                    for (int i = 0; i < currLocation.numItems(); i++) {
                        System.out.println(String.format("+ %s", currLocation.getItem(i).getName()));
                    }
                    break;
                }
                case "examine": {
                    if (commandParts.length == 1) {
                        System.out.println("I don't know how to do that");
                    } else if (currLocation.hasItem(commandParts[1])) {
                        System.out.println(currLocation.getItem(commandParts[1]).toString());
                    } else {
                        System.out.println(commandParts[1] + " does not exist");
                    }
                    break;
                }
                case "help": {
                    System.out.println("Commands:");
                    System.out.println("inventory - Display all items in your inventory");
                    System.out.println("take <item> - Take an item from the current location");
                    System.out.println("drop <item> - Drop an item into the current location");
                    System.out.println("look - Look around the current location");
                    System.out.println("examine <item> - Examine an item");
                    System.out.println("go <direction> - Move to a new location");
                    System.out.println("help - Display this help message");
                    System.out.println("quit - Quit the game");
                    break;
                }
                default: {
                    System.out.println("I don't know how to do that");
                    break;
                }
            }

        }
    }
}