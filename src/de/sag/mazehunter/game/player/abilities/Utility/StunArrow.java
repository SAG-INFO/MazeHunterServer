/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Utility;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.game.player.abilities.Entity.projectiles.StunArrowEntity;
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
        
        int index = getIndex(connectionID);
        int projectileID = Main.MAIN_SINGLETON.game.entityManager.getNewEntityID();
        
        Vector2 fVelocity = new Vector2(Config.STUNARROW_SPEED, 0);
        fVelocity.setAngle(angle);
        
        Main.MAIN_SINGLETON.game.entityManager.entities.add(new StunArrowEntity(fVelocity, Main.MAIN_SINGLETON.game.player[index].position.cpy(), Config.STUNARROW_HITBOXRADIUS2, projectileID, connectionID));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new StunArrowResponse(projectileID, connectionID, fVelocity.cpy(), angle));
        System.out.println("StunArrowResponse sent.");
        
        charge--;
        if (charge <= 0) {
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = null;
        }
    }
        
    public StunArrow() {
        charge = Config.STUNARROW_CHARGES;
    }
}
