/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.abilities.Utility;

import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.game.player.abilities.Ability;
import de.sag.mazehunter.game.player.abilities.Entity.nonMoving.TrapEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.TrapResponse;
import java.util.ArrayList;

/**
 *
 * @author Karl Huber
 */
public class Trap extends Ability{
    
    public int charge;
    public static ArrayList<ArrayList<Integer>> trapCount;
    
    @Override
    public void use(int connectionID, float angle) {
        
        int index = getIndex(connectionID);
        int entityID = Main.MAIN_SINGLETON.game.entityManager.getNewEntityID();
        
        Main.MAIN_SINGLETON.game.entityManager.entities.add(new TrapEntity(Main.MAIN_SINGLETON.game.player[index].position.cpy(), entityID, connectionID, Config.TRAP_HITBOXRADIUS2));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new TrapResponse(Main.MAIN_SINGLETON.game.player[index].position.cpy(), connectionID, entityID));
        System.out.println("TrapResponse sent.");
        
        trapCount.get(index).add(entityID);
        if (trapCount.get(index).size() > Config.TRAP_MAXTRAPCOUNT_PER_PLAYER) {
            Main.MAIN_SINGLETON.game.entityManager.disposeEntity(trapCount.get(index).get(0));
            trapCount.get(index).remove(0);
            System.out.println("Trap removed due to the player having more Traps then allowed. (not an error)");
        }
        
        
        
        charge--;
        if (charge <= 0) {
            Main.MAIN_SINGLETON.game.player[index].utilityAbility = null;
        }
    }
    
    public Trap() {
        charge = Config.TRAP_CHARGES;
        trapCount = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            trapCount.add(new ArrayList<>());
        }
    }
}