/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Entity.nonMoving;

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
}
