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
import de.sag.mazehunter.game.player.ability.entities.nonMoving.TrapEntity;
import de.sag.mazehunter.server.networkData.abilities.responses.TrapResponse;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Karl Huber
 */
public class Trap extends Ability{
    
    public int charge;
    public static HashMap<Integer, ArrayList<Integer>> trapCount;
    
    @Override
    public void use(int connectionID, float angle) {
        
        Player player = Main.MAIN_SINGLETON.game.getPlayer(connectionID);
        int entityID = Main.MAIN_SINGLETON.game.world.entityManager.getNewEntityID();
        
        Main.MAIN_SINGLETON.game.world.entityManager.entities.add(new TrapEntity(player.mc.position.cpy(), entityID, connectionID, Config.TRAP_HITBOXRADIUS2));
        Main.MAIN_SINGLETON.server.sendToAllUDP(new TrapResponse(player.mc.position.cpy(), connectionID, entityID));
        System.out.println("TrapResponse sent.");
        
        trapCount.get(1).add(entityID);
        if (trapCount.get(player.connectionID).size() > Config.TRAP_MAXTRAPCOUNT_PER_PLAYER) {
            Main.MAIN_SINGLETON.game.world.entityManager.disposeEntity(trapCount.get(player.connectionID).get(0));
            trapCount.get(player.connectionID).remove(0);
            System.out.println("Trap removed due to the player having more Traps then allowed. (not an error)");
        }
        
        charge--;
        if (charge <= 0) {
            player.ability = null;
        }
    }
    
    public Trap() {
        charge = Config.TRAP_CHARGES;
        trapCount = new HashMap<>();
        for (Player player : Main.MAIN_SINGLETON.game.players) {
            trapCount.put(player.connectionID, new ArrayList<>());
        }
    }
}