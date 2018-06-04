/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;

/**
 *
 * @author Karl Huber
 */
public class Pickup extends Ability {
    
     /**
     *
     * @param id id of the player collecting the pickup
     * @return true if he collects the Ability, false if he already has one
     */
    public boolean collect(int id) {
        return false;
    }
}
