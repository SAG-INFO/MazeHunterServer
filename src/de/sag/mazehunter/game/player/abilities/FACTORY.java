/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.game.player.abilities.Attack.Fireball;

/**
 *
 * @author Karl Huber
 * 
 * extends Ability just to get the getIndex() Method.
 */
public class FACTORY extends Ability {
    
    public boolean collectFireball(int id) {
        if (Main.MAIN_SINGLETON.game.player[getIndex(id)].attackAbility != null) {
            return false;
        } else {
            Main.MAIN_SINGLETON.game.player[getIndex(id)].attackAbility = new Fireball();
            System.out.println("Fireball collected.");
            return true; 
        }
    }

    public FACTORY() {
    }
}
