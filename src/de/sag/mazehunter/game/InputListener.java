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
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (Main.MAIN_SINGLETON.game.player[i].connectionID == connection.getID()) {
                index = i;
            }
        }
        if(object instanceof MovementRequest) {
            Main.MAIN_SINGLETON.game.player[index].move(((MovementRequest) object).angle, ((MovementRequest) object).movement);
            SendMovement(index);
            
        
        }
    }
    
    
    public void SendMovement(int index) {
        Main.MAIN_SINGLETON.game.outputer.sendMovementResponse(Main.MAIN_SINGLETON.game.player[index].position, Main.MAIN_SINGLETON.game.player[index].velocity, index);
    
    
    
    }
}