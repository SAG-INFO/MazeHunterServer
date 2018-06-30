/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.abilities.Attack.Fireball;
import de.sag.mazehunter.game.player.abilities.Attack.FrostBolt;
import de.sag.mazehunter.game.player.abilities.Utility.StandardHeal;
import de.sag.mazehunter.game.player.abilities.Utility.StunArrow;
import de.sag.mazehunter.game.player.abilities.Utility.Trap;

/**
 *
 * @author Karl Huber
 * 
 * extends Ability just to get the getIndex() Method.
 * 
 * collectWhatever() methods return the name of the old Abilityname if the player currently has one or null if the slot was empty.
 */
public class FACTORY extends Ability {
    
    public String getOldAttackName(int index) {
        Ability a = Main.MAIN_SINGLETON.game.player[index].attackAbility;
        if (a instanceof FrostBolt) {return "FrostBolt";}
        if (a instanceof Fireball) {return "Fireball";}
        
        return null;
    }
    
    public String getOldUtilityName(int index) {
        Ability a = Main.MAIN_SINGLETON.game.player[index].utilityAbility;
        if (a instanceof StandardHeal) {return "StandardHeal";}
        if (a instanceof StunArrow) {return "StunArrow";}
        if (a instanceof Trap) {return "Trap";}
        
        return null;
    }
    
    public String collectFireball(int id) {
        int index = getIndex(id);
        String old;
        if (Main.MAIN_SINGLETON.game.player[index].attackAbility != null) {
            old = getOldAttackName(index);
            Main.MAIN_SINGLETON.game.player[index].attackAbility = new FrostBolt();
            System.out.println("ATTACK: Abilities swapped. " + old);
            return old;
        } else {
            Main.MAIN_SINGLETON.game.player[index].attackAbility = new FrostBolt();
            System.out.println("ATTACK: Fireball collected.");
            return null;
        }
    }
    
    public String collectStandardHeal(int id) {
        int index = getIndex(id);
        String old;
        if (Main.MAIN_SINGLETON.game.player[index].utilityAbility != null) {
            old = getOldUtilityName(index);
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = new StandardHeal();
            System.out.println("UTILITY: Abilities swapped. " + old);
            return old;
        } else {
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = new StandardHeal();
            System.out.println("UTILITY: StandardHeal collected.");
            return null; 
        }
    }
    
    public String collectStunArrow(int id) {
        int index = getIndex(id);
        String old;
        if (Main.MAIN_SINGLETON.game.player[index].utilityAbility != null) {
            old = getOldUtilityName(index);
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = new StunArrow();
            System.out.println("UTILITY: Abilities swapped. " + old);
            return old;
        } else {
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = new StunArrow();
            System.out.println("UTILITY: StunArrow collected.");
            return null; 
        }
    }
    
    public String collectTrap(int id) {
        int index = getIndex(id);
        String old;
        if (Main.MAIN_SINGLETON.game.player[index].utilityAbility != null) {
            old = getOldUtilityName(index);
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = new Trap();
            System.out.println("UTILITY: Trap swapped. " + old);
            return old;
        } else {
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = new Trap();
            System.out.println("UTILITY: Trap collected.");
            return null; 
        }
    }

    public FACTORY() {
    }
}
