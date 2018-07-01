/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.abilities.Fireball;
import de.sag.mazehunter.game.player.ability.abilities.FrostBolt;
import de.sag.mazehunter.game.player.ability.abilities.StunArrow;
import de.sag.mazehunter.game.player.ability.abilities.Trap;

/**
 *
 * @author Karl Huber
 * 
 * extends Ability just to get the getIndex() Method.
 * 
 * collectWhatever() methods return the name of the old Abilityname if the player currently has one or null if the slot was empty.
 */
public class FACTORY extends Ability {
    
    public String getOldAttackName(Player player) {
        Ability a = player.ability;
        if (a instanceof FrostBolt) {return "FrostBolt";}
        if (a instanceof Fireball) {return "Fireball";}
        
        return null;
    }
    
    public String collectFireball(int id) {
        Player player = Main.MAIN_SINGLETON.game.getPlayer(id);
        String old;
        if (player.ability != null) {
            old = getOldAttackName(player);
            player.ability = new FrostBolt();
            System.out.println("ATTACK: Abilities swapped. " + old);
            return old;
        } else {
            player.ability = new FrostBolt();
            System.out.println("ATTACK: Fireball collected.");
            return null;
        }
    }
    
    public String collectStunArrow(int id) {
        Player player = Main.MAIN_SINGLETON.game.getPlayer(id);
        String old;
        if (player.ability != null) {
            old = getOldAttackName(player);
            player.ability = new StunArrow();
            System.out.println("UTILITY: Abilities swapped. " + old);
            return old;
        } else {
            player.ability = new StunArrow();
            System.out.println("UTILITY: StunArrow collected.");
            return null; 
        }
    }
    
    public String collectTrap(int id) {
        Player player = Main.MAIN_SINGLETON.game.getPlayer(id);
        String old;
        if (player.ability != null) {
            old = getOldAttackName(player);
            player.ability = new Trap();
            System.out.println("UTILITY: Trap swapped. " + old);
            return old;
        } else {
            player.ability = new Trap();
            System.out.println("UTILITY: Trap collected.");
            return null; 
        }
    }

    public FACTORY() {
    }
}
