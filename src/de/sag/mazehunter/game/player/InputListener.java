/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.MovementResponse;
import de.sag.mazehunter.utils.Vector2;

/**
 *
 * @author Karl Huber
 * 
 * Parent class for all the Listeners.
 * 
 * Also holds the send...() methods used by multiple Listeners.
 */
public class InputListener extends Listener {

    public int getIndex (int id){
        int index = 0;
        for (int i = 0; i < 4; i++) {
            Player p = Main.MAIN_SINGLETON.game.player[i];
            if (p!=null && p.connectionID == id) {
                index = i;
            }
        }
        return index;
    }
    
    public static void sendMovementResponse(Player player){
        sendMovementResponse(player.position, player.requestedVelocity, player.connectionID);
    }
    
    public static void sendMovementResponse(Vector2 position, Vector2 velocity, int id) {
        MovementResponse mr = new MovementResponse(position, velocity, id);
        Main.MAIN_SINGLETON.server.sendToAllUDP(mr);
    }
}