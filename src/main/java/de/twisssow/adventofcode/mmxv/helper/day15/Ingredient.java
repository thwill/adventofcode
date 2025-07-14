package de.twisssow.adventofcode.mmxv.helper.day15;

public class Ingredient {

    String name;
    int capacity;
    int durability;
    int flavor;
    int texture;
    int calories;

    public Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        this.name = name;
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }


    public int getCapacity() {
        return capacity;
    }


    public int getDurability() {
        return durability;
    }


    public int getFlavor() {
        return flavor;
    }


    public int getTexture() {
        return texture;
    }


    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
