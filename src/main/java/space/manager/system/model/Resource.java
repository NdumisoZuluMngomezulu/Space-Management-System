package main.java.space.manager.system.model;

public class Resource {
    public enum ResourceType{
        FOOD,
        WEAPON,
        TOOL,
        DEVICE,
        CHEMICAL
    }

    ResourceType type;
    private String name;
    private double quantity;
    private double criticalLevel;

    public Resource(ResourceType type, double size, double level){
        if (size < 0 || level < 0){throw new IllegalArgumentException("HHAYI WENA");}
        this.type = type;
        this.quantity = size;
        this.criticalLevel = level;
        this.name = "oxygen";
    }

    public Resource(String name, ResourceType type, double size, double level){
        if (size < 0 || level < 0){throw new IllegalArgumentException("HHAYI WENA");}
        this.name = name;
        this.type = type;
        this.quantity = size;
        this.criticalLevel = level;
    }
    
    public ResourceType type(){return this.type;}
    public String name(){return this.name;}
    public void setName(String name){this.name = name;}
    public double quantity(){return this.quantity;}
    public double critical(){return this.criticalLevel;}
    public void consume(double size){this.quantity -= size;}
    public void restock(double size){this.quantity += size;}

    public String toString(){return this.name + ", stocked level is " + this.quantity + " critical level is " + this.criticalLevel;}

}
/*
| `restock(double)` | Adds quantity |
| `isCritical()` | Returns true if quantity <= criticalLevel |
| `toString()` | Human-readable summary | */
