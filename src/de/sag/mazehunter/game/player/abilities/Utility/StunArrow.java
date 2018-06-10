/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Utility;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.game.player.abilities.projectiles.FireballProjectile;
import de.sag.mazehunter.server.networkData.abilities.responses.FireballResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 */
public class StunArrow extends Ability{
    
    public int charge;
    public String name = "StunArrow";
    
    @Override
    public void use(int connectionID, float angle) {
        
        int index = getIndex(connectionID);
        int projectileID = Main.MAIN_SINGLETON.game.projectileManager.getNewProjectileID();
        
        Vector2 fVelocity = new Vector2(Config.STUNARROW_SPEED, 0);
        fVelocity.setAngle(angle);
        
        Main.MAIN_SINGLETON.game.projectileManager.projectilesNoC.add(new FireballProjectile(fVelocity, Main.MAIN_SINGLETON.game.player[index].position, Config.STUNARROW_HITBOXRADIUS2, projectileID, Main.MAIN_SINGLETON.game.player[index].position, connectionID));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new FireballResponse(connectionID, fVelocity.cpy()));
        
        charge--;
        if (charge == 0) {
            Main.MAIN_SINGLETON.game.player[index].attackAbility = null;
        }
    }
        
    public StunArrow() {
        charge = Config.FIREBALL_CHARGES;
    }
}