package de.twisssow.adventofcode.mmxv;

import de.twisssow.adventofcode.mmxv.helper.day22.BossFight;
import de.twisssow.adventofcode.mmxv.helper.day22.State;

public class Day22 {


    public static void main(String[] args) {

        State initial = new State();
        initial.playerHitPoints = 50;
        initial.playerMana = 500;
        initial.bossHitPoints = 71;
        initial.bossDamage = 10;

        new BossFight().play(initial, true, false);
        System.out.println("Minimum mana spent to win: " + BossFight.bestManaSpent);

        State initialHardFight = new State();
        initialHardFight.playerHitPoints = 50;
        initialHardFight.playerMana = 500;
        initialHardFight.bossHitPoints = 71;
        initialHardFight.bossDamage = 10;


        BossFight.bestManaSpent = Integer.MAX_VALUE;
        new BossFight().play(initialHardFight, true, true);
        System.out.println("Minimum mana spent to win: " + BossFight.bestManaSpent);



    }
}
