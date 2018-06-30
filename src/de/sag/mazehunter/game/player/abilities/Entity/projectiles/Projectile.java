/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity.projectiles;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.abilities.Entity.AbilityEntity;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public abstract class Projectile extends AbilityEntity {
    
    public final Vector2 velocity = new Vector2();
    public final Vector2 startPosition = new Vector2();
    public float maxRange2;
    public boolean collidesWithWalls;
    
    public void collideWithWalls() {}

    public Projectile(Vector2 velocity, Vector2 position, float radius, int id, int connectionID, float maxRange2) {
        this.velocity.set(velocity);
        this.position.set(position);
        this.radius2 = radius;
        this.entityID = id;
        this.startPosition.set(position.cpy());
        this.maxRange2 = maxRange2;
        this.connectionID = connectionID;
    }
    
    private final Vector2 tmp1 = new Vector2();
    private final Vector2 tmp2 = new Vector2();
    public void update(float delta) {
        
        this.position.add(tmp1.set(velocity).scl(delta));

        for (Player player : Main.MAIN_SINGLETON.game.players) {
            if(player == null)
                continue;
            
            //collide with player
            if (tmp2.set(player.position).sub(position).len2() < radius2 + Config.PLAYER_HITBOXRADIUS2 && player.connectionID != connectionID) {
                shoot(player, entityID);
            }
            
            //maxrange reached
            if (tmp2.set(player.position).sub(position).len2() < radius2 + Config.PLAYER_HITBOXRADIUS2 && player.connectionID != connectionID) {
                Main.MAIN_SINGLETON.game.world.entityManager.disposeEntity(this);
            }
            
            if (collidesWithWalls) {
                collideWithWalls();
            }
        }
    }
    
    
}

