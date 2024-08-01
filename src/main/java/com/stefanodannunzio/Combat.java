package com.stefanodannunzio;

import java.util.Random;

import com.stefanodannunzio.characters.Character;
import com.stefanodannunzio.characters.Elf;
import com.stefanodannunzio.characters.Orc;

public class Combat {
    private Character player1;
    private Character player2;
    private Random random = new Random();
    private boolean player1Starts;

    public Combat(Character player1, Character player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Starts = random.nextBoolean();
    }

    public void start() {
        while (player1.getHealth() > 0 && player2.getHealth() > 0) {
            performRound();
        }

        if (player1.getHealth() <= 0) {
            GameLog.writeToBothConsoleAndFile(player2.getName() + " wins the combat!");
        } else {
            GameLog.writeToBothConsoleAndFile(player1.getName() + " wins the combat!");
        }
    }

    private void performRound() {
        GameLog.writeToBothConsoleAndFile("Round start!");

        for (int i = 0; i < 7; i++) {
            if (player1Starts) {
                performAttack(player1, player2);
                if (player2.getHealth() <= 0) break;
                performAttack(player2, player1);
                if (player1.getHealth() <= 0) break;
            } else {
                performAttack(player2, player1);
                if (player1.getHealth() <= 0) break;
                performAttack(player1, player2);
                if (player2.getHealth() <= 0) break;
            }
        }

        player1Starts = !player1Starts;
        GameLog.writeToBothConsoleAndFile("Round end!");
    }

    private void performAttack(Character attacker, Character defender) {
        double shotPower = attacker.getDexterity() * attacker.getStrength() * attacker.getLevel();
        double shotEffectiveness = random.nextInt(100) + 1;
        double attackValue = shotPower * shotEffectiveness;
        double defensePower = defender.getArmor() * defender.getSpeed();

        double damage = ((attackValue - defensePower) / 500) * 100;
        if (attacker instanceof Elf) {
            damage *= 1.05;
        } else if (attacker instanceof Orc) {
            damage *= 1.1;
        }

        defender.updateHealth(damage);
        if (defender.getHealth() < 0) {
            defender.setHealth(0);
        }
        GameLog.writeToBothConsoleAndFile(attacker.getName() + ' ' + attacker.getNickname() + " attacks " + defender.getName() + ' ' + defender.getNickname()  + " and deals " + damage + " damage. " + defender.getName() + " has " + defender.getHealth() + " health left.");
    }
}