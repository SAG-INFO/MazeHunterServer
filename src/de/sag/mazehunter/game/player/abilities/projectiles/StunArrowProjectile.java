/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.projectiles;

import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class StunArrowProjectile extends Projectile {
    
    @Override
    public void shoot(Player player) {
        player.changeHealth(-Config.FIREBALL_DAMAGE);
        //TODO shoot Animation (?)
    }
    
    public StunArrowProjectile(Vector2 velocity, Vector2 position, float radius, int id, Vector2 startPosition, int connectionID) {
        super(velocity, position, radius, id, startPosition, connectionID);
    }
}
