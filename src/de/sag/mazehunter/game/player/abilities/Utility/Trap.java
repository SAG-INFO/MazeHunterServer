/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Utility;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.game.player.abilities.Entity.nonMoving.IntWrapper;
import de.sag.mazehunter.game.player.abilities.Entity.nonMoving.TrapNonMoving;
import de.sag.mazehunter.server.networkData.abilities.responses.TrapResponse;

/**
 *
 * @author Karl Huber
 */
public class Trap extends Ability{
    
    public int charge;
    
    @Override
    public void use(int connectionID, float angle) {
        
        int index = getIndex(connectionID);
        int entityID = Main.MAIN_SINGLETON.game.entityManager.getNewEntityID();
        
        Main.MAIN_SINGLETON.game.nonMovingManager.nonMoving.add(new TrapNonMoving(Main.MAIN_SINGLETON.game.player[index].position.cpy(), entityID, connectionID, Config.TRAP_HITBOXRADIUS2));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new TrapResponse(Main.MAIN_SINGLETON.game.player[index].position.cpy(), connectionID, entityID));
        System.out.println("TrapResponse sent.");
        
        Main.MAIN_SINGLETON.game.nonMovingManager.trapCount.get(index).add(new IntWrapper(entityID));
        if (Main.MAIN_SINGLETON.game.nonMovingManager.trapCount.get(index).size() > Config.TRAP_MAXTRAPCOUNT_PER_PLAYER) {
            Main.MAIN_SINGLETON.game.nonMovingManager.disposeNonMoving(Main.MAIN_SINGLETON.game.nonMovingManager.trapCount.get(index).get(0).integer);
            Main.MAIN_SINGLETON.game.nonMovingManager.trapCount.get(index).remove(0);
            System.out.println("Trap removed due to the player having more Traps then allowed. (not an error)");
        }
                
        charge--;
        if (charge <= 0) {
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = null;
        }
    }
        
    public Trap() {
        charge = Config.TRAP_CHARGES;
    }
}