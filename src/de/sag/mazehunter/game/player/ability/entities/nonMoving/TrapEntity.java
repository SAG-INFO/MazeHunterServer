/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.entities.nonMoving;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.entities.AbilityEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.TrapShootResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class TrapEntity extends AbilityEntity{

    @Override
    protected void playerCollision(Player player) {
        Main.MAIN_SINGLETON.server.sendToAllTCP(new TrapShootResponse(player.connectionID, entityID));
        player.mc.slow(0, Config.TRAP_ROOTDURATION);
    }
    
    public TrapEntity(Vector2 position, int entityID, int connectionID, int radius2) {
        super(position, entityID, connectionID, radius2);
    }
}
