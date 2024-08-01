package com.stefanodannunzio.characters;

import java.time.LocalDate;

public class Elf extends Character {
    public Elf(String name, String nickname, LocalDate birthDate) {
        super(name, nickname, birthDate);
        this.health = 900;
        this.speed = 9;
        this.dexterity = 5;
        this.strength = 4;
        this.armor = 4;
    }


    @Override
    public void updateHealth(double damage) {
        this.health -= damage;
    }
}