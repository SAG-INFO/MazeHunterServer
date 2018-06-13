/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity;

/**
 *
 * @author Karl Huber
 */
public class EntityManager {
    
    private int entityID;
    
    public int getNewEntityID() {
        entityID++;
        return entityID;
    }
}
