package de.twisssow.adventofcode.mmxv.helper.day22;

import java.util.Set;

public class TurnAndReturn {

    Set<Spell> availableSpells;
    Set<Spell> activeSpells;


    int playerHitPoints;
    int bossHitPoints;
    int playerMana;

    int usedMana;

    public RoundResult doTurn(Set<Spell> availableSpells, Set<Spell> activeSpells, int playerHitPoints, int bossHitPoints, int playerMana, int usedMana) {
        // Implement the logic for the turn here
        // This method should return RoundResult based on the game state after the turn

        // Example placeholder logic:
        if (playerHitPoints <= 0) {
            return RoundResult.BOSS_WINS;
        } else if (bossHitPoints <= 0) {
            return RoundResult.PLAYER_WINS;
        } else {
            // Continue the game
            return RoundResult.CONTINUE;
        }
    }

}

