/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity.projectiles;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.server.networkData.abilities.responses.FrostBoltShootResponse;
import de.sag.mazehunter.utils.Vector2;


/**
 *
 * @author Karl Huber
 */
public class FrostBoltEntity extends Projectile {
    
    @Override
    public void shoot(Player player, int projectileID) {
        player.changeHealth(-Config.FROSTBOLT_DAMAGE);
        
        Main.MAIN_SINGLETON.server.sendToAllUDP(new FrostBoltShootResponse(player.connectionID, projectileID));
    }
    
    public FrostBoltEntity(Vector2 velocity, Vector2 position, float radius, int id, int connectionID) {
        super(velocity, position, radius, id, connectionID, Config.FROSTBOLT_MAXRANGE2);
    }
}
