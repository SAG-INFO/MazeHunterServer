/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.Player;
import de.sag.mazehunter.game.player.ability.Ability;
import de.sag.mazehunter.game.player.ability.entities.projectiles.StunArrowEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.StunArrowResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class StunArrow extends Ability{
    
    public int charge;
    
    @Override
    public void use(int connectionID, float angle) {
        
        Player player = Main.MAIN_SINGLETON.game.getPlayer(connectionID);
        int projectileID = Main.MAIN_SINGLETON.game.world.entityManager.getNewEntityID();
        
        Vector2 fVelocity = new Vector2(Config.STUNARROW_SPEED, 0);
        fVelocity.setAngle(angle);
        
        Main.MAIN_SINGLETON.game.world.entityManager.entities.add(new StunArrowEntity(fVelocity, player.mc.position.cpy(), Config.STUNARROW_HITBOXRADIUS2, projectileID, connectionID));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new StunArrowResponse(projectileID, connectionID, fVelocity.cpy(), angle));
        System.out.println("StunArrowResponse sent.");
        
        charge--;
        if (charge <= 0) {
            player.ability = null;
        }
    }
        
    public StunArrow() {
        charge = Config.STUNARROW_CHARGES;
    }
}
