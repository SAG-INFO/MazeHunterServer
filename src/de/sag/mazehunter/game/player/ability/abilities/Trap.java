/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player.ability.abilities;

import de.sag.mazehunter.game.player.ability.Ability;

/**
 *
 * @author Karl Huber
 */
public class Trap extends Ability{
    
//    public int charge;
//    public static ArrayList<ArrayList<Integer>> trapCount;
    
    @Override
    public void use(int connectionID, float angle) {
//        
//        Player player = Main.MAIN_SINGLETON.game.getPlayer(connectionID);
//        int entityID = Main.MAIN_SINGLETON.game.world.entityManager.getNewEntityID();
//        
//        Main.MAIN_SINGLETON.game.world.entityManager.entities.add(new TrapEntity(player.position.cpy(), entityID, connectionID, Config.TRAP_HITBOXRADIUS2));
//        Main.MAIN_SINGLETON.server.sendToAllUDP(new TrapResponse(player.position.cpy(), connectionID, entityID));
//        System.out.println("TrapResponse sent.");
//        
//        trapCount.get(1).add(entityID);
//        if (trapCount.get(index).size() > Config.TRAP_MAXTRAPCOUNT_PER_PLAYER) {
//            Main.MAIN_SINGLETON.game.world.entityManager.disposeEntity(trapCount.get(index).get(0));
//            trapCount.get(index).remove(0);
//            System.out.println("Trap removed due to the player having more Traps then allowed. (not an error)");
//        }
//        
//        charge--;
//        if (charge <= 0) {
//            Main.MAIN_SINGLETON.game.player[index].utilityAbility = null;
//        }
    }
    
    public Trap() {
//        charge = Config.TRAP_CHARGES;
//        trapCount = new ArrayList<>();
//        for (Player player : Main.MAIN_SINGLETON.game.player) {
//            trapCount.set(player.connectionID, new ArrayList<>());
//        }
    }
}