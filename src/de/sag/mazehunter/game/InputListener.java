/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.sag.mazehunter.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import de.sag.mazehunter.Main;
import de.sag.mazehunter.server.networkData.MovementRequest;

/**
 *
 * @author g.duennweber
 */
public class InputListener extends Listener{

    @Override
    public void received(Connection connection, Object object) {
        if(object instanceof MovementRequest) {
            Main.MAIN_SINGLETON.game.player[this.getIndex(connection.getID())].move(((MovementRequest) object).angle, ((MovementRequest) object).movement);
            SendMovement(connection.getID());
        }
    }
    
    
    public void SendMovement(int id) {
        Main.MAIN_SINGLETON.game.outputer.sendMovementResponse(Main.MAIN_SINGLETON.game.player[this.getIndex(id)].position, Main.MAIN_SINGLETON.game.player[this.getIndex(id)].velocity, id);
    
    }
    
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
}
