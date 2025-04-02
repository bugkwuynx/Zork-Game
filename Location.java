import java.util.ArrayList;
import java.util.HashMap;

public class Location {

    // Member Variables
    private String name;
    private String description;
    private ArrayList<Item> itemList;
    private HashMap<String, Location> connections;

    // Constructor
    public Location(String pName, String pDesc) {
        name = pName;
        description = pDesc;
        itemList = new ArrayList<>();
        connections = new HashMap<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setters
    public void setName(String pName) {
        name = pName;
    }

    public void setDescription(String pDesc) {
        description = pDesc;
    }

    public void addItem(Item pItem) {
        itemList.add(pItem);
    }

    public boolean hasItem(String pCheck) {
        for (int i = 0; i < itemList.size(); i++) {
            if ((itemList.get(i)).getName().equalsIgnoreCase(pCheck)) {
                return true;
            }
        }
        return false;
    }

    public Item getItem(String pCheck) {
        for (int i = 0; i < itemList.size(); i++) {
            if ((itemList.get(i)).getName().equalsIgnoreCase(pCheck)) {
                return itemList.get(i);
            }
        }
        return null;
    }

    public Item getItem(int index) {
        if (index >= 0 && index < itemList.size()) {
            return itemList.get(index);
        }
        return null;
    }

    public int numItems() {
        return itemList.size();
    }

    public Item removeItem(String pCheck) {
        for (int i = 0; i < itemList.size(); i++) {
            if ((itemList.get(i)).getName().equalsIgnoreCase(pCheck)) {
                Item hold = itemList.get(i);
                itemList.remove(itemList.get(i));
                return hold;
            }
        }
        return null;
    }

    public void connect(String pDirection, Location pLocation) {
        connections.put(pDirection, pLocation);
    }

    public boolean canMove(String pDirection) {
        return connections.containsKey(pDirection);
    }

    public Location getLocation(String pDirection) {
        return connections.get(pDirection);
    }
}
