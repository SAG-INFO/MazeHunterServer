/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.abilities.Attack.Fireball;
import de.sag.mazehunter.game.player.abilities.Utility.StandardHeal;

/**
 *
 * @author Karl Huber
 * 
 * extends Ability just to get the getIndex() Method.
 */
public class FACTORY extends Ability {
    
    public boolean collectFireball(int id) {
        if (Main.MAIN_SINGLETON.game.player[getIndex(id)].attackAbility != null) {
            System.out.println("ATTACK: You already have an attack Ability.");
            return false;
        } else {
            Main.MAIN_SINGLETON.game.player[getIndex(id)].attackAbility = new Fireball();
            System.out.println("ATTACK: Fireball collected.");
            return true; 
        }
    }
    
    public boolean collectStandardHeal(int id) {
        if (Main.MAIN_SINGLETON.game.player[getIndex(id)].utilityAbility != null) {
            System.out.println("UTILITY: You already have a utility Ability.");
            return false;
        } else {
            Main.MAIN_SINGLETON.game.player[getIndex(id)].utilityAbility = new StandardHeal();
            System.out.println("UTILITY: StandardHeal collected.");
            return true; 
        }
    }

    public FACTORY() {
    }
}
