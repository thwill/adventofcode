package de.twisssow.adventofcode.mmxv.helper.day22;

public class Spell {

    String name;

    int manaCost;

    int manaGain;

    int mana;

    int damage;

    int heal;

    int armour;

    int duration;

    public Spell(String name, int manaCost, int manaGain, int damage, int heal, int armour, int duration) {
        this.name = name;
        this.manaCost = manaCost;
        this.manaGain = manaGain;
        this.damage = damage;
        this.heal = heal;
        this.armour = armour;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getDamage() {
        return damage;
    }

    public int getHeal() {
        return heal;
    }

    public int getArmour() {
        return armour;
    }

    public int getDuration() {
        return duration;
    }
}
