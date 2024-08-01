package com.stefanodannunzio.characters;

import java.time.LocalDate;

public class Orc extends Character {
    public Orc(String name, String nickname, LocalDate birthDate) {
        super(name, nickname, birthDate);
        this.health = 1200;
        this.speed = 5;
        this.dexterity = 3;
        this.strength = 8;
        this.armor = 6;
    }


    @Override
    public void updateHealth(double damage) {
        this.health -= damage;
    }
}
