package main.java.space.manager.system;

public class Resources {
    public enum ResourceType{
        FOOD,
        WEAPON,
        TOOL,
        DEVICE,
        CHEMICAL
    }

    ResourceType type;
    private double quantity;
    private double criticalLevel;

    

}
/*
* `type`
* `quantity`
* `criticalLevel`

---

## Validation Rules

* `quantity < 0` → throw `IllegalArgumentException`
* `criticalLevel < 0` → throw `IllegalArgumentException`

---

## Methods

| Method | Details |
| ----------------- | ----------------------------------------- |
| `type()` | Returns resource type |
| `quantity()` | Returns quantity |
| `criticalLevel()` | Returns critical level |
| `consume(double)` | Reduces quantity |
| `restock(double)` | Adds quantity |
| `isCritical()` | Returns true if quantity <= criticalLevel |
| `toString()` | Human-readable summary | */
