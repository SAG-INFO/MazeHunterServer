/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.ActiveAbility;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author arein
 */
public class Teleport extends ActiveAbility{

    public Teleport(int playerId) {
        super(playerId, 2, 5);
    }

    @Override
    protected void fire(Vector2 targetPosition) {
        Player player = Main.MAIN_SINGLETON.game.getPlayer(playerId);
        player.mc.setPosition(targetPosition);
        player.mc.forceMovementUpdate();
    }
}
