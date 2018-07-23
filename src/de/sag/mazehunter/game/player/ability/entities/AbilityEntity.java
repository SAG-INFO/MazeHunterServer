/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.entities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author sreis
 */
public class AbilityEntity{

    protected final Vector2 tmpVec = new Vector2();
    
    public final Vector2 position = new Vector2();
    public int entityID;
    public int connectionID;
    public float radius2;
    
    public AbilityEntity(Vector2 position, int entityID, int connectionID, float radius2) {
        this.position.set(position);
        this.entityID = entityID;
        this.connectionID = connectionID;
        this.radius2 = radius2;
    }
    
    public void update(float delta) {
        for (Player player : Main.MAIN_SINGLETON.game.players) {
            if(player == null)
                continue;
            
            if (tmpVec.set(player.mc.position).sub(position).len2() < radius2 + Config.PLAYER_HITBOXRADIUS2 && player.connectionID != connectionID) {
                playerCollision(player);
            }
        }
    }
    
    protected void playerCollision(Player player){}
}
