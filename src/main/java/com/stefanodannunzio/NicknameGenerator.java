package com.stefanodannunzio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NicknameGenerator {
    private List<String> nicknames;
    private Random random;

    public NicknameGenerator() {
        nicknames = new ArrayList<>();
        
        nicknames.add("The Shadow");
        nicknames.add("The Wanderer");
        nicknames.add("The Silent One");
        nicknames.add("The Fox");
        nicknames.add("The Raven");
        nicknames.add("The Viper");
        nicknames.add("The Storm");
        nicknames.add("The Reaper");
        nicknames.add("The Jester");
        nicknames.add("The Sage");
        nicknames.add("The Knight");
        nicknames.add("The Rogue");
        nicknames.add("The Bard");
        nicknames.add("The Druid");
        nicknames.add("The Warlock");
        nicknames.add("The Paladin");
        nicknames.add("The Ranger");
        nicknames.add("The Sorcerer");
        nicknames.add("The Cleric");
        nicknames.add("The Wizard");
        nicknames.add("The Alchemist");
        nicknames.add("The Artificer");
        nicknames.add("The Gunslinger");
        nicknames.add("The Inquisitor");
        nicknames.add("The Hunter");
        nicknames.add("The Pathfinder");
        nicknames.add("The Outlander");
        nicknames.add("The Mystic");
        nicknames.add("The Adept");
        nicknames.add("The Champion");
        nicknames.add("The Protector");
        nicknames.add("The Guardian");
        nicknames.add("The Sentinel");
        nicknames.add("The Vigilante");
        nicknames.add("The Outlaw");
        nicknames.add("The Mercenary");
        nicknames.add("The Bounty Hunter");
        nicknames.add("The Assassin");
        nicknames.add("The Spy");
        nicknames.add("The Diplomat");
        nicknames.add("The Scholar");
        nicknames.add("The Explorer");
        nicknames.add("The Adventurer");
        nicknames.add("The Pioneer");
        nicknames.add("The Survivor");
        nicknames.add("The Lone Wolf");
        nicknames.add("The Anti-Hero");
        nicknames.add("The Villain");
        
        random = new Random();
    }

    public String generateNickname() {
    
        int index = random.nextInt(nicknames.size());
        return nicknames.get(index);
    }
}