package de.twisssow.adventofcode.mmxv.helper.day22;

public class BossFight {

    public static int bestManaSpent = Integer.MAX_VALUE;

    public void play(State state, boolean playerTurn, boolean hardFight) {
        // apply effects
        applyEffects(state);

        // check if someone died
        if (state.bossHitPoints <= 0) {
            bestManaSpent = Math.min(bestManaSpent, state.spentMana);
            return;
        }
        if (state.playerHitPoints <= 0) {
            return;
        }


        // prune bad paths
        if (state.spentMana >= bestManaSpent) {
            return;
        }

        if (playerTurn) {
            if (hardFight) {
                // hart fight: player loses 1 hit point
                state.playerHitPoints--;
                if (state.playerHitPoints <= 0) {
                    return; // player dies
                }
            }
            // try each possible spell
            for (Spell spell : Spell.values()) {
                if (!canCast(state, spell)) continue;
                State next = state.copy();
                cast(next, spell);
                play(next, false, hardFight);
            }

        } else {
            // boss turn: attack
            State next = state.copy();
            int damage = Math.max(1, next.bossDamage - (next.shieldTimer > 0 ? 7 : 0));
            next.playerHitPoints -= damage;

            play(next, true, hardFight);
        }
    }

    void applyEffects(State state) {
        if (state.shieldTimer > 0) {
            state.shieldTimer--;
        }
        if (state.poisonTimer > 0) {
            state.bossHitPoints -= 3;
            state.poisonTimer--;
        }
        if (state.rechargeTimer > 0) {
            state.playerMana += 101;
            state.rechargeTimer--;
        }
    }

    boolean canCast(State state, Spell spell) {
        switch (spell) {
            case MAGIC_MISSILE:
                return state.playerMana >= 53;
            case DRAIN:
                return state.playerMana >= 73;
            case SHIELD:
                return state.playerMana >= 113 && state.shieldTimer == 0;
            case POISON:
                return state.playerMana >= 173 && state.poisonTimer == 0;
            case RECHARGE:
                return state.playerMana >= 229 && state.rechargeTimer == 0;
            default:
                return false;
        }
    }

    void cast(State state, Spell spell) {
        switch (spell) {
            case MAGIC_MISSILE:
                state.playerMana -= 53;
                state.spentMana += 53;
                state.bossHitPoints -= 4;
                break;
            case DRAIN:
                state.playerMana -= 73;
                state.spentMana += 73;
                state.bossHitPoints -= 2;
                state.playerHitPoints += 2;
                break;
            case SHIELD:
                state.playerMana -= 113;
                state.spentMana += 113;
                state.shieldTimer = 6;
                break;
            case POISON:
                state.playerMana -= 173;
                state.spentMana += 173;
                state.poisonTimer = 6;
                break;
            case RECHARGE:
                state.playerMana -= 229;
                state.spentMana += 229;
                state.rechargeTimer = 5;
                break;
        }


    }
}
