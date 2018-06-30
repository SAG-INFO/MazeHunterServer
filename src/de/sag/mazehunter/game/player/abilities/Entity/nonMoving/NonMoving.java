/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity.nonMoving;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.abilities.Entity.AbilityEntity;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public abstract class NonMoving extends AbilityEntity {

    public NonMoving(Vector2 position, int entityID, int connectionID, int radius2) {
        this.position.set(position);
        this.entityID = entityID;
        this.connectionID = connectionID;
        this.radius2 = radius2;
    }
    
    private final Vector2 tmpVec = new Vector2();
    @Override
    public void update(float delta) {
        
        for (Player player : Main.MAIN_SINGLETON.game.players) {
            if(player == null)
                continue;
            
            if (tmpVec.set(player.position).sub(position).len2() < radius2 + Config.PLAYER_HITBOXRADIUS2 && player.connectionID != connectionID) {
                shoot(player, entityID);
                Main.MAIN_SINGLETON.game.world.entityManager.disposeEntity(this);
            }
        }
    }
}
