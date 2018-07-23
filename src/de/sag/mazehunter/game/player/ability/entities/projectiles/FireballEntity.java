/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.entities.projectiles;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.utils.Vector2;


/**
 *
 * @author Karl Huber
 */
public class FireballEntity extends Projectile {
    public FireballEntity(Vector2 position, int playerId, int connectionID) {
        super(position, playerId, connectionID, Config.FIREBALL_HITBOXRADIUS2, Config.FIREBALL_MAXRANGE2);
    }
    
    @Override
    protected void playerCollision(Player player) {
        player.mc.slow(0, 4);
        Main.MAIN_SINGLETON.game.world.entityManager.disposeEntity(this);
    }
}
