package com.stefanodannunzio;

import java.util.ArrayList;
import java.util.List;
import com.stefanodannunzio.characters.Character;

public class Player {
    private List<Character> characters;

    public Player() {
        characters = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public List<Character> getCharacters() {
        return characters;
    }

    boolean hasCharacters() {
        return !characters.isEmpty();
    }

    public Character getRandomCharacter() {
        int index = (int) (Math.random() * characters.size());
        return characters.get(index);
    }

    public void removeCharacter(Character character) {
        characters.remove(character);
    }

    public String getCharactersInfo() {
        StringBuilder sb = new StringBuilder();
        for (Character character : characters) {
            sb.append(character.getName()).append(" - ").append(character.getNickname()).append("\n");
        }
        return sb.toString();
    }
}