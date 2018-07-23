/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.player.ability.ActiveAbility;
import de.sag.mazehunter.game.player.ability.entities.nonMoving.SatanEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.SatanResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author arein
 */
public class Satan extends ActiveAbility{

    public Satan(int playerId) {
        super(playerId, 2, 4);
    }

    @Override
    protected void fire(Vector2 targetPosition) {
        SatanResponse data = new SatanResponse();
        
        SatanEntity entity = new SatanEntity(targetPosition, Main.MAIN_SINGLETON.game.world.entityManager.getNewEntityID(), playerId);
        Main.MAIN_SINGLETON.game.world.entityManager.entities.add(entity);
        
        data.position.set(targetPosition);
        data.playerId = playerId;
        Main.MAIN_SINGLETON.server.sendToAllTCP(data);
    }
    
}
