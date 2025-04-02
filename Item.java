public class Item {
    private String name;
    private String type;
    private String description;

    // Constructor
    public Item(){
        name = "Unknown";
        type = "Unknown";
        description = "Unknown";
    }

    public Item(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }

    // Getters
    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getDescription(){
        return description;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String toString(){
        return name + " [ " + type + " ]: " + description;
    }
}