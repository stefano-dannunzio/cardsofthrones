package com.stefanodannunzio.characters;

import java.time.LocalDate;

public class Human extends Character {
    public Human(String name, String nickname, LocalDate birthDate) {
        super(name, nickname, birthDate);
        this.health = 1000;
        this.speed = 7;
        this.dexterity = 4;
        this.strength = 6;
        this.armor = 5;
    }


    @Override
    public void updateHealth(double damage) {
        this.health -= damage;
    }
}
