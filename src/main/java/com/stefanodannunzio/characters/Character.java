package com.stefanodannunzio.characters;

import java.time.LocalDate;
import java.time.Period;

public abstract class Character {
    protected String name;
    protected String nickname;
    protected LocalDate birthDate;
    protected int age;
    protected int health;
    protected int speed;
    protected int dexterity;
    protected int strength;
    protected int level;
    protected int armor;

    public Character(String name, String nickname, LocalDate birthDate) {
        this.name = name;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.age = calculateAge(birthDate);
        this.level = 1;
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public abstract void updateHealth(double damage);

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public void improveStats() {
        this.level++;
        
    }



}