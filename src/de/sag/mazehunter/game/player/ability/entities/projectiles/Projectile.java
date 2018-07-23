/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.entities.projectiles;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.entities.AbilityEntity;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public abstract class Projectile extends AbilityEntity {
    
    public final Vector2 velocity = new Vector2();
    public final Vector2 origin = new Vector2();
    public float maxRange2;
    
    private boolean flying;
    
    public Projectile(Vector2 position, int connectionId, int entityId, float radius, float maxRange2) {
        super(position, entityId, connectionId, radius);
        this.maxRange2 = maxRange2;
    }
    
    public void shoot(Vector2 velocity){
        shoot(velocity, position);
    }
    
    public void shoot(Vector2 velocity, Vector2 position){
        this.velocity.set(velocity);
        this.position.set(position);
        this.origin.set(position);
        this.flying = true;
    }
    
    public void update(float delta) {
        super.update(delta);
        if(!flying)
            return;
        
        this.position.add(tmpVec.set(velocity).scl(delta));

        for (Player player : Main.MAIN_SINGLETON.game.players) {
            if(player == null)
                continue;
            
            //maxrange reached
            if (tmpVec.set(origin).sub(position).len2() > maxRange2) {
                maxRangeReached();
            }
            
            //TODO Wall Collisions
        }
    }
    
    protected void wallCollision(){
        
    }
    
    protected void maxRangeReached(){
        Main.MAIN_SINGLETON.game.world.entityManager.disposeEntity(this);
    }
}

