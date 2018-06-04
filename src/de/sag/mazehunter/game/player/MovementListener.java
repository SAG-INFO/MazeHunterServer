/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game.player;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.game.Config;
import de.sag.mazehunter.server.networkData.MovementRequest;

/**
 *
 * @author g.duennweber
 */
public class MovementListener extends Listener{
    
    /*sending the configs from startGame() causes the client to disconnect.
    This is an alternative where the first movement input triggers the server 
    to send the configs to everyone.
    */
    boolean first = true;

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MovementRequest) {
            Main.MAIN_SINGLETON.game.players[this.getIndex(connection.getID())].move(((MovementRequest) object).angle, ((MovementRequest) object).movement);
            SendMovement(connection.getID());
            if (first) {
                Config.pushConfig();
                first = false;
            }
        }
    }
    
    public void SendMovement(int id) {
        Main.MAIN_SINGLETON.game.outputer.sendMovementResponse(Main.MAIN_SINGLETON.game.players[this.getIndex(id)].position, Main.MAIN_SINGLETON.game.players[this.getIndex(id)].velocity, id);
    }
    
    public int getIndex (int id){
        int index = 0;
        for (int i = 0; i < 4; i++) {
            Player p = Main.MAIN_SINGLETON.game.players[i];
            if (p!=null && p.connectionID == id) {
                index = i;
            }
        }
        return index;
    }
}
