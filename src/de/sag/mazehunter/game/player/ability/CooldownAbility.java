/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability;

/**
 *
 * @author arein
 */
public abstract class CooldownAbility {
    protected final float cooldown;
    protected float cooldownLeft;

    public final int connectionID;
    
    public CooldownAbility(int playerId, float cooldown) {
        this.connectionID = playerId;
        this.cooldown = cooldown;
    }
    
    public void update(float delta){
        cooldownLeft -= delta;
    }
    
    public void startCooldown(){
        cooldownLeft = cooldown;
    }
    
    public boolean ready(){
        return cooldownLeft<=0;
    }
}
