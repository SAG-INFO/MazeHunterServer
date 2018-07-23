/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.ActiveAbility;
import de.sag.mazehunter.game.player.ability.entities.nonMoving.TrapEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.TrapResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class Trap extends ActiveAbility{
    
    @Override
    public void fire(Vector2 p) {
        Player player = Main.MAIN_SINGLETON.game.getPlayer(connectionID);
        int entityID = Main.MAIN_SINGLETON.game.world.entityManager.getNewEntityID();
        
        Main.MAIN_SINGLETON.game.world.entityManager.entities.add(new TrapEntity(player.mc.position.cpy(), entityID, connectionID, Config.TRAP_HITBOXRADIUS2));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new TrapResponse(player.mc.position.cpy(), connectionID, entityID));
    }
    
    public Trap(int playerID) {
        super(playerID, 3, 1);
    }
}