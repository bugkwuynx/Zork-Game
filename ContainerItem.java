import java.util.ArrayList;

public class ContainerItem extends Item {
    private ArrayList<Item> items;

    public ContainerItem(String name, String type, String description) {
        super(name, type, description);
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    public Item removeItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        String result = super.toString() + ":\n";
        for (Item item : items) {
            result += item.getName() + "\n";
        }
        return result;
    }
}
