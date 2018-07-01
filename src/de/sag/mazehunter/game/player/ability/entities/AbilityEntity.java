/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.entities;

import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author sreis
 */
public class AbilityEntity {

    public final Vector2 position = new Vector2();
    public int entityID;
    public int connectionID;
    public float radius2;

    /**
     * called when entity gets triggered by some event, most of the time when it
     * collides with a player.
     */
    public void shoot(Player player, int entityID) {
    }

    public void update(float delta) {

    }
}
