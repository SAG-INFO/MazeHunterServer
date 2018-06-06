/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Attack;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;

/**
 *
 * @author Karl Huber
 * 
 * extends Ability just to get the getIndex() Method.
 */
public class PickupCollector extends Ability {
    
    public boolean collectFireball(int id) {
        if (Main.MAIN_SINGLETON.game.player[getIndex(id)].attackAbility != null) {
            return false;
        } else {
            Main.MAIN_SINGLETON.game.player[getIndex(id)].attackAbility = new Fireball();
            return true; 
        }
    }

    public PickupCollector() {
        System.out.println("TESTING: pickupCollector created");
    }
}
