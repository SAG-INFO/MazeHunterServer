/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities;

/**
 *
 * @author Karl Huber
 */
public abstract class Ability {
    
    public void use(int id, float angle) {}
    public void use(int id) {}
    public void use(int id, int dir) {}
    
    public void startCooldown() {}
}
