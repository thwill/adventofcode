package de.twisssow.adventofcode.mmxv.day21;

public class EquipmentItem implements Comparable<EquipmentItem> {

    String name;

    String type;

    int cost;

    int damage;

    int armour;

    public EquipmentItem(String name, String type, int cost, int damage, int armour) {
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.damage = damage;
        this.armour = armour;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getArmour() {
        return armour;
    }

    @Override
    public int compareTo(EquipmentItem o) {
        return Integer.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return "EquipmentItem{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", cost=" + cost +
                ", damage=" + damage +
                ", armour=" + armour +
                '}';
    }
}
