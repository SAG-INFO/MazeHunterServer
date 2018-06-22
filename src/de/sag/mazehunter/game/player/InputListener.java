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
        for (int i = 0; i < 4; i++) {
            Player p = Main.MAIN_SINGLETON.game.player[i];
            if (p!=null && p.connectionID == id) {
                return i;
            }
        }
        return -1;
    }
    
    public static void sendMovementResponse(Player player){
        sendMovementResponse(player.position, player.velocity, player.connectionID);
    }
    
    public static void sendMovementResponse(Vector2 position, Vector2 velocity, int id) {
        MovementResponse mr = new MovementResponse(position, velocity, id);
        Main.MAIN_SINGLETON.server.sendToAllUDP(mr);
    }
}