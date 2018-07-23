/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.pickups;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.ActiveAbility;
import de.sag.mazehunter.game.player.ability.abilities.Fireball;
import de.sag.mazehunter.game.player.ability.abilities.SpeedBoost;
import de.sag.mazehunter.game.player.ability.abilities.StunArrow;
import de.sag.mazehunter.game.player.ability.abilities.Teleport;

/**
 *
 * @author Karl Huber
 * 
 * extends Ability just to get the getIndex() Method.
 * 
 * collectWhatever() methods return the name of the old Abilityname if the player currently has one or null if the slot was empty.
 */
public class FACTORY{
    
    public void equipAbility(String abilityName, int id){
        Player player = Main.MAIN_SINGLETON.game.getPlayer(id);
        ActiveAbility nuAbility = null;
        switch(abilityName){
            case "fireball":
                nuAbility = new Fireball(id);
                break;
            case "speedboost":
                nuAbility = new SpeedBoost(id);
                break;
            case "stunarrow":
                nuAbility = new StunArrow(id);
                break;
            case "teleport":
                nuAbility = new Teleport(id);
                break;
            default:
                System.err.println("ability does not exist");
        }
        player.activeAbility = nuAbility;
    }
}
