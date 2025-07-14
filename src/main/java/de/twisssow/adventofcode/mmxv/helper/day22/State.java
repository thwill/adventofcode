package de.twisssow.adventofcode.mmxv.helper.day22;

public class State {

    public int playerHitPoints;
    public int playerMana;

    public int bossHitPoints;
    public int bossDamage;

    public int spentMana;

    public int shieldTimer;
    public int poisonTimer;
    public int rechargeTimer;

    State copy() {
        State state = new State();
        state.playerHitPoints = this.playerHitPoints;
        state.playerMana = this.playerMana;
        state.bossHitPoints = this.bossHitPoints;
        state.bossDamage = this.bossDamage;
        state.spentMana = this.spentMana;
        state.shieldTimer = this.shieldTimer;
        state.poisonTimer = this.poisonTimer;
        state.rechargeTimer = this.rechargeTimer;
        return state;
    }

}
