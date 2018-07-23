/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.ability.abilities.NoAbility;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public abstract class ActiveAbility extends CooldownAbility{
    protected final int maxUsages;
    public int usagesLeft;
    
    public ActiveAbility(int playerId, int maxUsages){
        this(playerId, maxUsages, 0);
    }
    
    public ActiveAbility(int playerId, int maxUsages, float cooldown){
        super(playerId, cooldown);
        this.maxUsages = maxUsages;
        this.usagesLeft = maxUsages;
    }
    
    void use(Vector2 targetPosition){
        if(!super.ready())
            return;
        
        this.usagesLeft--;
        super.startCooldown();
        fire(targetPosition);
        
        if(usagesLeft == 0)
            dequip();
    }
    
    protected abstract void fire(Vector2 targetPosition);
    
    protected void dequip(){
        Main.MAIN_SINGLETON.game.getPlayer(connectionID).activeAbility = new NoAbility(connectionID);
    }
    
    private final Vector2 tmp = new Vector2();
    protected float calcAngle(Vector2 target){
        tmp.set(target);
        tmp.sub(Main.MAIN_SINGLETON.game.getPlayer(connectionID).mc.position);
        return tmp.angle();
    }
}
